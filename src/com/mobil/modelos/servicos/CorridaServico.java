package com.mobil.modelos.servicos;

import com.mobil.modelos.corrida.Corrida;
import com.mobil.modelos.corrida.CorridaComum;
import com.mobil.modelos.corrida.CorridaDeLuxo;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.propriedades.Localizacao;

public class CorridaServico {
    private static final int VELOCIDADE_MEDIA_KMH = 60;

    public Corrida criarCorrida(String tipoCorrida, Motorista motorista,
                                float dinheiroDisponivel, int metodoPagamento,
                                Passageiro passageiro, Localizacao destino) {

        switch (tipoCorrida.toUpperCase()) {
            case "COMUM":
                return new CorridaComum(motorista, dinheiroDisponivel,
                        metodoPagamento, passageiro, destino);
            case "DE LUXO":
            case "LUXO":
                return new CorridaDeLuxo(motorista, dinheiroDisponivel,
                        metodoPagamento, passageiro, destino);
            default:
                throw new IllegalArgumentException("Tipo de corrida inválido: " + tipoCorrida);
        }
    }

    public static int calcularTempo(Localizacao p, Localizacao m) {
        float distanciaKm = (float) Localizacao.getDistancia(p, m);
        return (int)((distanciaKm / VELOCIDADE_MEDIA_KMH) * 60); // Retorna em minutos
    }
}