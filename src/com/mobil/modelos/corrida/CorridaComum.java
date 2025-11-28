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
    public CorridaComum(int indiceMotorista, ArrayList<Motorista> motoristas,
                        float dinheiroDisponivel, float distancia, int mPagamento, Passageiro passageiro, Localizacao destino) {
        this.setPassageiro(passageiro);
        this.setLocalizacaoDestino(destino);
        float precoCorrida = tarifaBaseComum + distancia * multiplicadorComum;
        this.setMotorista(motoristas.get(indiceMotorista)); // da a referência para o motorista mais próximo disponível

        switch (mPagamento) {
            case 1 -> metodoDePagamento = new Dinheiro(dinheiroDisponivel, precoCorrida);
            case 2 -> {
                String chavePIX = this.getMotorista().chavePIX;
                metodoDePagamento = new PIX(dinheiroDisponivel, precoCorrida, chavePIX);
            }
             // não implementado
            case 3 -> metodoDePagamento = new CartaoDeCredito(dinheiroDisponivel, precoCorrida); // não implementado
        }

        System.out.printf("\nTudo pronto para iniciar a corrida.\nMotorista encontrado: %s", this.getMotorista().getNome());
        sc.nextLine();
        corridaChamada();
        iniciarCorrida();
    }

    public void corridaChamada() {
        //
        System.out.println("Motorista a caminho.\nCarro: ");
        System.out.printf("%s %s %s - %s \n", this.getMotorista().getVeiculo().getMarca(), this.getMotorista().getVeiculo().getModelo(),
                this.getMotorista().getVeiculo().getCor(), this.getMotorista().getVeiculo().getPlaca());

        // mudar status do motorista e corrida
        this.getMotorista().setStatus("Motorista a caminho do passageito");
        this.setStatus("Motorista a caminho do passageiro.");

        // tempo estimado IMPLEMENTAR

        int motoristaX = this.getMotorista().getLocalizacao().getX(), motoristaY = this.getMotorista().getLocalizacao().getY();
        int passageiroX = this.getPassageiro().getLocalizacao().getX(), passageiroY = this.getPassageiro().getLocalizacao().getY();

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

            System.out.printf("Motorista está na posição [%d] [%d], a %.2f metros de você!\n", motoristaX, motoristaY, this.getLocalizacao().getDistancia(getPassageiro(),getMotorista()) );

            // Atualização do tempo esperado

        }

        System.out.printf("\nO %s chegou no seu destino!", this.getMotorista().getNome());
        System.out.println("Enter para iniciar a corrida.");
        //Limpa buffer
        if (sc.hasNextLine()) {
            sc.nextLine(); // Limpa qualquer entrada pendente
        }
        String input = sc.nextLine();
    }

    public void iniciarCorrida() {
        System.out.println("Corrida iniciada");

        // Posição atual do passageiro (que está sendo levado)
        int passageiroX = this.getPassageiro().getLocalizacao().getX();
        int passageiroY = this.getPassageiro().getLocalizacao().getY();

        // Posição do destino final
        int destinoX = this.getLocalizacaoDestino().getX();
        int destinoY = this.getLocalizacaoDestino().getY();

        // O motorista também se move junto com o passageiro
        int motoristaX = this.getMotorista().getLocalizacao().getX();
        int motoristaY = this.getMotorista().getLocalizacao().getY();


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

            // Atualiza as posições
            this.getPassageiro().getLocalizacao().setX(passageiroX);
            this.getPassageiro().getLocalizacao().setY(passageiroY);
            this.getMotorista().getLocalizacao().setX(motoristaX);
            this.getMotorista().getLocalizacao().setY(motoristaY);


            double distancia = Math.sqrt(
                    Math.pow(motoristaX - destinoX, 2) +
                    Math.pow(motoristaY - destinoY, 2)
            );

            System.out.printf("Motorista está na posição [%d] [%d], a %.2f metros do destino final!\n",
                    motoristaX, motoristaY, distancia);
        }
    }
    public void finalizarCorrida() {
        // Pagamento
    }
}
