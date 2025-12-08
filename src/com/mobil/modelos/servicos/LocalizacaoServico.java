package com.mobil.modelos.servicos;

import com.mobil.modelos.propriedades.Localizacao;

public class LocalizacaoServico {

    public static Localizacao criarLocalizacao(int x, int y) {
        validarCoordenadas(x, y);
        return new Localizacao(x, y);
    }

    public Localizacao solicitarLocalizacaoUsuario(String mensagem) {
        System.out.println(mensagem);

        System.out.print("Coordenada X (0-100): ");
        int x = Utilidades.lerInteiro();

        System.out.print("Coordenada Y (0-100): ");
        int y = Utilidades.lerInteiro();

        validarCoordenadas(x, y);
        return new Localizacao(x, y);
    }

    private static void validarCoordenadas(int x, int y) {
        if (x < 0 || x > Localizacao.xMax || y < 0 || y > Localizacao.yMax) {
            throw new IllegalArgumentException(
                    "Coordenadas inválidas. X e Y devem estar entre 0 e " + Localizacao.xMax
            );
        }
    }

    public static float calcularDistanciaEmKM(Localizacao origem, Localizacao destino) {
        // Considerando que 1 unidade = 1 km
        return (float) Localizacao.getDistancia(origem, destino);
    }
}
