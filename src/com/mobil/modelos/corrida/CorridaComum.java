package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.*;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;

import java.util.ArrayList;


public class CorridaComum extends Corrida{
    private static int tarifaBaseComum = 5;
    private static int multiplicadorComum = 1;
    private MetodoDePagamento metodoDePagamento;

    // mPagamento : 1 = Dinheiro, 2 = PIX, 3 = CartaoDeCredito
    public CorridaComum(int indiceMotorista, ArrayList<Motorista> motoristas,
                        float dinheiroDisponivel, float distancia, int mPagamento, Passageiro passageiro) {
        this.setPassageiro(passageiro);
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
    }

    public void corridaChamada() {
        //
        System.out.println("Motorista a caminho.\nCarro: ");
        System.out.printf("%s %s %s - %s", this.getMotorista().getVeiculo().getMarca(), this.getMotorista().getVeiculo().getModelo(),
                this.getMotorista().getVeiculo().getCor(), this.getMotorista().getVeiculo().getPlaca());

        // mudar status do motorista e corrida
        this.getMotorista().setStatus("Motorista a caminho do passageito");
        this.setStatus("Motorista a caminho do passageiro.");

        // tempo estimado IMPLEMENTAR

        int motoristaX = this.getMotorista().getLocalizacao().getX(), motoristaY = this.getMotorista().getLocalizacao().getY();
        int passageiroX = this.getPassageiro().getLocalizacao().getX(), passageiroY = this.getPassageiro().getLocalizacao().getY();

        while (motoristaX != passageiroX && motoristaY != passageiroY) {
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

            System.out.printf("Motorista está na posição [%d] [%d], a %d metros de você!", motoristaX, motoristaY);

            // Atualização do tempo esperado


            // System.out.println("Enter para continuar: ");
            // sc.nextLine();
        }

        System.out.printf("\nO %s chegou no seu destino!", this.getMotorista().getNome());
        System.out.println("Enter para iniciar a corrida.");
        sc.nextLine();
    }

    public void iniciarCorrida() {
        // loop da corrida
    }

    public void finalizarCorrida() {
        // Pagamento
    }
}
