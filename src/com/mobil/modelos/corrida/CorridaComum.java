package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.*;
import com.mobil.modelos.pessoas.*;
import com.mobil.modelos.propriedades.Localizacao;


public class CorridaComum extends Corrida{
    private static int tarifaBaseComum = 5;
    private static int multiplicadorComum = 1;
    private MetodoDePagamento metodoDePagamento;

    public float calcularPrecoCorrida(float distancia){
        return tarifaBaseComum + distancia * (float) multiplicadorComum;
    }

    public CorridaComum(Motorista motorista,
                        float dinheiroDisponivel, int mPagamento, Passageiro passageiro, Localizacao destino) {
       super(motorista, dinheiroDisponivel, mPagamento, passageiro, destino);
    }

}
