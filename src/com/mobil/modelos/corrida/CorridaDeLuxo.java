package com.mobil.modelos.corrida;

import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.propriedades.Localizacao;

public class CorridaDeLuxo extends Corrida{
    private static int tarifaBaseLuxo = 9;
    private static float multiplicadorLuxo = 2.2f;

    public CorridaDeLuxo(Motorista motorista, float dinheiroDisponivel, int mPagamento, Passageiro passageiro, Localizacao destino) {
        super(motorista, dinheiroDisponivel, mPagamento, passageiro, destino);
    }


    @Override
    public float calcularPrecoCorrida(float distancia) {
        return 0;
    }
}
