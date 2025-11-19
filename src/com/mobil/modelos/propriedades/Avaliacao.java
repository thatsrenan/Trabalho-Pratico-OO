package com.mobil.modelos.propriedades;

import java.util.Scanner;

public class Avaliacao {
    private int totalDeAvaliacoes;
    private float mediaAvaliacoes;
    private int somaDasAvaliacoes;

    Scanner sc = new Scanner(System.in);

    // SEMPRE DE 0 a 5!
    // Construtor do zero
    public Avaliacao() {
        totalDeAvaliacoes = 0;
        mediaAvaliacoes = 0;
        somaDasAvaliacoes = 0;
    }

    public Avaliacao(int totalDeAvaliacoes, int somaDasAvaliacoes) {
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.somaDasAvaliacoes = somaDasAvaliacoes;
        mediaAvaliacoes = (float) somaDasAvaliacoes / totalDeAvaliacoes;
    }

    public void avaliar() {
        System.out.println("Avalie sua experiência de 0 a 5 estrelas!");

        int avaliacao;

        while (true) {
            avaliacao = sc.nextInt();

            if (avaliacao < 0 || avaliacao > 5) {
                System.out.println("A avaliação deve ser de 0 a 5! Tente novamente:");
            } else {
                break; // saiu do loop, avaliação válida
            }
        }

        somaDasAvaliacoes += avaliacao;
        totalDeAvaliacoes++;

        mediaAvaliacoes = (float) somaDasAvaliacoes / totalDeAvaliacoes;
    }

    public float getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
}
