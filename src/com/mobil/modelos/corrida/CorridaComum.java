package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.*;

public class CorridaComum extends Corrida{
    private static int tarifaBaseComum;
    private static int multiplicadorComum;
    private MetodoDePagamento metodoDePagamento;


    // mPagamento : 1 = Dinheiro, 2 = PIX, 3 = CartaoDeCredito
    public CorridaComum(float dinheiroDisponivel, float distancia, int mPagamento) {
        float precoCorrida = tarifaBaseComum + distancia * multiplicadorComum;

        switch (mPagamento) {
            case 1 -> metodoDePagamento = new Dinheiro(dinheiroDisponivel, precoCorrida);
            case 2 -> metodoDePagamento = new PIX(); // não implementado
            case 3 -> metodoDePagamento = new CartaoDeCredito(); // não implementado
        }

    }
}
