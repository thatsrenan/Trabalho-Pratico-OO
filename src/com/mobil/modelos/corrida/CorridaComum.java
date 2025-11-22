package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.*;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Usuario;

import java.util.ArrayList;


public class CorridaComum extends Corrida{
    private static int tarifaBaseComum = 5;
    private static int multiplicadorComum = 1;
    private MetodoDePagamento metodoDePagamento;

    // mPagamento : 1 = Dinheiro, 2 = PIX, 3 = CartaoDeCredito
    public CorridaComum(int indiceMotorista, ArrayList<Motorista> motoristas,
                        float dinheiroDisponivel, float distancia, int mPagamento) {
        float precoCorrida = tarifaBaseComum + distancia * multiplicadorComum;
        this.setMotorista(motoristas.get(indiceMotorista)); // da a referência para o motorista mais próximo disponível

        switch (mPagamento) {
            case 1 -> metodoDePagamento = new Dinheiro(dinheiroDisponivel, precoCorrida);
            case 2 -> {
                String chavePIX = this.getMotorista().chavePIX;
                metodoDePagamento = new PIX(dinheiroDisponivel, precoCorrida, chavePIX);
            }
             // não implementado
            case 3 -> metodoDePagamento = new CartaoDeCredito(); // não implementado
        }

        System.out.println("Tudo pronto para iniciar a corrida.");
        sc.nextLine();
        iniciarCorrida();
    }

    public void iniciarCorrida() {

    }

    public void finalizarCorrida() {

    }
}
