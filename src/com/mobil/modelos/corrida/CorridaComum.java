package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.*;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.propriedades.Localizacao;

import java.util.ArrayList;


public class CorridaComum extends Corrida{
    private static int tarifaBaseComum = 5;
    private static int multiplicadorComum = 1;
    private MetodoDePagamento metodoDePagamento;

    // mPagamento : 1 = Dinheiro, 2 = PIX, 3 = CartaoDeCredito
    public CorridaComum(Motorista motorista,
                        float dinheiroDisponivel, int mPagamento, Passageiro passageiro, Localizacao destino) {
        this.setPassageiro(passageiro);
        this.setLocalizacaoDestino(destino);
        float distancia = (float) Localizacao.getDistancia(getPassageiro().getLocalizacao(), destino);
        float precoCorrida = tarifaBaseComum + distancia * (float) multiplicadorComum;
        this.setMotorista(motorista); // da a referência para o motorista mais próximo disponível


        switch (mPagamento) {
            case 1 -> metodoDePagamento = new Dinheiro(dinheiroDisponivel, precoCorrida);
            case 2 -> metodoDePagamento = new PIX(dinheiroDisponivel, precoCorrida); // não implementado
            case 3 -> metodoDePagamento = new CartaoDeCredito(dinheiroDisponivel, precoCorrida); // não implementado
        }

        System.out.printf("\nTudo pronto para iniciar a corrida.\nR$%.2f por %.2f km\nMotorista encontrado: %s\n", precoCorrida, distancia,this.getMotorista().getNome());

        System.out.println("""
                1 - Chamar o motorista
                2 - Cancelar
                """);

        boolean respostaValidaNaoComputada = true;
        while(respostaValidaNaoComputada) {
            System.out.println("Resposta: ");
            int resposta = sc.nextInt();
            switch(resposta) {
                case 1 -> {
                    corridaChamada();
                    respostaValidaNaoComputada = false;
                }
                case 2 -> {
                    System.out.println("Corrida cancelada.\n\n");
                    cancelarCorrida();
                    respostaValidaNaoComputada = false;
                }
                default -> {
                    System.out.println("Resposta inválida...\n");
                }
            }
        }

        sc.nextLine();
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
                        motoristaX, motoristaY, this.getLocalizacao().getDistancia(getPassageiro().getLocalizacao(),getMotorista().getLocalizacao()));
                contador = -1;

                System.out.printf("%d minutos para ele chegar!", calcularTempo(getPassageiro().getLocalizacao(), getMotorista().getLocalizacao()));

                System.out.println("Enter para continuar.");
                if (sc.hasNextLine()) {
                    sc.nextLine(); // Limpa qualquer entrada pendente
                }
            }
            contador++;
        }

        System.out.printf("\n%s está te esperando!\n", this.getMotorista().getNome());
        System.out.println("Enter para entrar no carro e iniciar a corrida.");

        //Limpa buffer
        if (sc.hasNextLine()) {
            sc.nextLine(); // Limpa qualquer entrada pendente
        }

        corrida();
    }

    public void corrida() {
        System.out.println("Corrida iniciada.");

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
                        motoristaX, motoristaY, calcularTempo(getPassageiro().getLocalizacao(), getLocalizacaoDestino()));
                contador = -1;

                System.out.println("Enter para continuar");
                if (sc.hasNextLine()) {
                    sc.nextLine(); // Limpa qualquer entrada pendente
                }
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
        metodoDePagamento.pagar();
    }

    public void cancelarCorrida() {

    }

    public int calcularTempo(Localizacao p, Localizacao m) {
        // velocidade média: 60 km/h
        float distancia = (float) Localizacao.getDistancia(p, m);
        return (int)((distancia/60) * 60); // return em minutos int
    }
}
