package com.mobil.modelos.propriedades;

import java.util.Scanner;

public class Avaliacao {
    private int totalDeAvaliacoes;
    private float mediaAvaliacoes;
    private int somaDasAvaliacoes;


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

    public void avaliar(Scanner sc) {
        System.out.println("\n=== AVALIE A CORRIDA ===");
        System.out.println("1 ⭐ - Péssimo");
        System.out.println("2 ⭐⭐ - Ruim");
        System.out.println("3 ⭐⭐⭐ - Regular");
        System.out.println("4 ⭐⭐⭐⭐ - Bom");
        System.out.println("5 ⭐⭐⭐⭐⭐ - Excelente");
        System.out.println("Digite sua nota (1-5): ");

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
        System.out.println("✅ Avaliação registrada!");
    }

    public void calcularMediaAvaliacoes(){

    }

    public float getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
}
