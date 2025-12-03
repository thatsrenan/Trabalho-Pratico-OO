package com.mobil.modelos.servicos;

import com.mobil.modelos.propriedades.Localizacao;

import java.util.Scanner;

public class LocalizacaoServico {
    private Scanner sc = new Scanner(System.in);

    public static Localizacao criarLocalizacao(int x, int y) {
        validarCoordenadas(x, y);
        return new Localizacao(x, y);
    }

    public Localizacao solicitarLocalizacaoUsuario(String mensagem) {
        System.out.println(mensagem);

        System.out.print("Coordenada X (0-100): ");
        int x = sc.nextInt();

        System.out.print("Coordenada Y (0-100): ");
        int y = sc.nextInt();

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

    public float calcularDistanciaEmKM(Localizacao origem, Localizacao destino) {
        // Considerando que 1 unidade = 1 km
        return (float) Localizacao.getDistancia(origem, destino);
    }

    public int estimarTempoDeslocamento(Localizacao origem, Localizacao destino) {
        float distancia = calcularDistanciaEmKM(origem, destino);
        // Velocidade média de 60 km/h
        return (int) ((distancia / 60) * 60); // retorna em minutos
    }
}
