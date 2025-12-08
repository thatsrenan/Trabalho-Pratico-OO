package com.mobil.modelos.corrida;

import com.mobil.modelos.excecoes.*;
import com.mobil.modelos.pagamento.MetodoDePagamento;
import com.mobil.modelos.pessoas.*;
import com.mobil.modelos.propriedades.Avaliacao;
import com.mobil.modelos.propriedades.Localizacao;
import com.mobil.modelos.servicos.*;

public abstract class Corrida {
    private String status; // Motorista a caminho, Em andamento, Finalizando
    private Motorista motorista;
    private Passageiro passageiro;
    private Localizacao localizacaoDestino;
    private MetodoDePagamento metodoDePagamento;
    private CorridaServico corridaServico = new CorridaServico();
    private PagamentoServico pagamentoServico;
    private Avaliacao avaliacao;

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public abstract float calcularPrecoCorrida(float distancia);

    public Corrida(Motorista motorista, float dinheiroDisponivel, int mPagamento, Passageiro passageiro, Localizacao destino) {

        this.setPassageiro(passageiro);
        this.setLocalizacaoDestino(destino);
        float distancia = (float) Localizacao.getDistancia(getPassageiro().getLocalizacao(), destino);
        float precoCorrida = calcularPrecoCorrida(distancia);
        this.setMotorista(motorista); // da a referência para o motorista mais próximo disponível

        pagamentoServico = new PagamentoServico();

        // Chama o servico para criar de fato o metodoDePagamento de acordo com o int mPagamento
        metodoDePagamento = pagamentoServico.criarMetodoPagamento(mPagamento, dinheiroDisponivel, precoCorrida, passageiro);

        System.out.printf("\nTudo pronto para iniciar a corrida.\nR$%.2f por %.2f km\nMotorista encontrado: %s\n", precoCorrida, distancia,this.getMotorista().getNome());

        System.out.println("""
                1 - Chamar o motorista
                2 - Cancelar
                """);

        boolean respostaValidaComputada = false;
        while(!respostaValidaComputada) {
            System.out.println("Resposta: ");
            int resposta = Utilidades.lerInteiro();
            switch(resposta) {
                case 1 -> {
                    corridaChamada();
                    respostaValidaComputada = true;
                }
                case 2 -> {
                    System.out.println("Corrida cancelada.\n\n");
                    cancelarCorrida();
                    respostaValidaComputada = true;
                }
                default -> {
                    System.out.println("Resposta inválida...\n");
                }
            }
        }

        Utilidades.pausar();
    }

    public void corridaChamada() {
        //
        System.out.println("Motorista a caminho.\n");
        System.out.printf("Carro: %s %s %s - %s \n", this.getMotorista().getVeiculo().getMarca(), this.getMotorista().getVeiculo().getModelo(),
                this.getMotorista().getVeiculo().getCor(), this.getMotorista().getVeiculo().getPlaca());

        // atualizacao de status do motorista e da corrida
        this.getMotorista().setStatus("Motorista a caminho do passageiro");
        this.setStatus("Motorista a caminho do passageiro");

        int motoristaX = this.getMotorista().getLocalizacao().getX(), motoristaY = this.getMotorista().getLocalizacao().getY();
        int passageiroX = this.getPassageiro().getLocalizacao().getX(), passageiroY = this.getPassageiro().getLocalizacao().getY();

        int contador = 0;
        while (motoristaX != passageiroX || motoristaY != passageiroY) {

            // loop do motorista chegando no passageiro
            if (motoristaX > passageiroX) {
                motoristaX--;
            } else if (motoristaX < passageiroX) {
                motoristaX++;
            }

            if (motoristaY > passageiroY) {
                motoristaY--;
            } else if (motoristaY < passageiroY) {
                motoristaY++;
            }

            this.getMotorista().getLocalizacao().setX(motoristaX);
            this.getMotorista().getLocalizacao().setY(motoristaY);

            // o contador é para não spamar atualizações do motorista no terminal
            if (motoristaX != passageiroX && motoristaY != passageiroY && contador > 1) {
                System.out.printf("Motorista está na posição [%d] [%d], a %.2f metros de você!\n",
                        motoristaX, motoristaY, LocalizacaoServico.calcularDistanciaEmKM(getPassageiro().getLocalizacao(),getMotorista().getLocalizacao()));
                contador = -1;

                System.out.printf("%d minutos para ele chegar!", CorridaServico.calcularTempo(getPassageiro().getLocalizacao(), getMotorista().getLocalizacao()));

                System.out.println("\nEnter para continuar.");
                Utilidades.pausar();
            }
            contador++;
        }

        System.out.printf("\n%s está te esperando!\n", this.getMotorista().getNome());
        System.out.println("Enter para entrar no carro e iniciar a corrida.");

        //Limpa buffer
        Utilidades.pausar();

        corrida();
    }

    public void corrida() {
        System.out.println("Corrida iniciada.");

        // Atualização dos status
        this.setStatus("Em corrida");
        this.getMotorista().setStatus("Em corrida");

        // Posição atual do passageiro (que está sendo levado)
        int passageiroX = this.getPassageiro().getLocalizacao().getX();
        int passageiroY = this.getPassageiro().getLocalizacao().getY();

        // Posição do destino final
        int destinoX = this.getLocalizacaoDestino().getX();
        int destinoY = this.getLocalizacaoDestino().getY();

        // O motorista também se move junto com o passageiro
        int motoristaX = this.getMotorista().getLocalizacao().getX();
        int motoristaY = this.getMotorista().getLocalizacao().getY();

        int contador = 0;
        while (passageiroX != destinoX || passageiroY != destinoY) {
            // Movimento do PASSAGEIRO em direção ao destino
            if (destinoX > passageiroX) {
                passageiroX++;
            } else if (destinoX < passageiroX) {
                passageiroX--;
            }

            if (destinoY > passageiroY) {
                passageiroY++;
            } else if (destinoY < passageiroY) {
                passageiroY--;
            }

            // Motorista se move JUNTO com o passageiro (mesma posição)
            motoristaX = passageiroX;
            motoristaY = passageiroY;

            if (passageiroX != destinoX && passageiroY != destinoY && contador > 1) {
                System.out.printf("Vocês estão na posição [%d] [%d]. %d minutos para chegar ao destino!\n",
                        motoristaX, motoristaY, CorridaServico.calcularTempo(getPassageiro().getLocalizacao(), getLocalizacaoDestino()));
                contador = -1;

                System.out.println("Enter para continuar");
                Utilidades.pausar();
            }

            // Atualiza as posições reais dos objetos
            this.getPassageiro().getLocalizacao().setX(passageiroX);
            this.getPassageiro().getLocalizacao().setY(passageiroY);
            this.getMotorista().getLocalizacao().setX(motoristaX);
            this.getMotorista().getLocalizacao().setY(motoristaY);

            contador++;
        }

        finalizarCorrida();
    }

    public void finalizarCorrida() {
        System.out.println("Você chegou ao seu destino!");
        try {
            metodoDePagamento.pagar();
        } catch (SaldoInsuficienteException e) {
            System.out.println("\n❌ ERRO NO PAGAMENTO: " + e.getMessage());
            System.out.println("A corrida não pode ser finalizada.");
            return; // Sai sem fazer avaliação
        } catch (PagamentoBloqueadoException e) {
            System.out.println("\n❌ ERRO DE SEGURANÇA: " + e.getMessage());
            System.out.println("Entre em contato com seu banco.");
            this.setStatus("Pagamento falhou");
            return;
        }

        // Atualização de status
        this.setStatus("Finalizada");
        this.getMotorista().setStatus("Disponível");
        // Limpa buffer
        Utilidades.pausar();
        // Implementação da Avaliação
        avaliacao = this.getMotorista().getAvaliacao();
        AvaliacaoServico.avaliar(avaliacao);

    }

    public void cancelarCorrida() {
        System.out.println("Corrida cancelada.");
    }

}