package com.mobil.modelos.servicos;

import com.mobil.modelos.propriedades.Avaliacao;

import java.util.Scanner;

public class AvaliacaoServico {
    public static void avaliar(Scanner sc, Avaliacao avaliacao) {
        System.out.println("\n=== AVALIE A CORRIDA ===");
        System.out.println("1 ⭐ - Péssimo");
        System.out.println("2 ⭐⭐ - Ruim");
        System.out.println("3 ⭐⭐⭐ - Regular");
        System.out.println("4 ⭐⭐⭐⭐ - Bom");
        System.out.println("5 ⭐⭐⭐⭐⭐ - Excelente");
        System.out.println("Digite sua nota (1-5): ");

        int respsotavaliacao;

        while (true) {
            respsotavaliacao = sc.nextInt();

            if (respsotavaliacao < 0 || respsotavaliacao > 5) {
                System.out.println("A avaliação deve ser de 0 a 5! Tente novamente:");
            } else {
                break; // saiu do loop, avaliação válida
            }
        }

        avaliacao.somaUmaAvaliacao();

        avaliacao.calcularMediaAvaliacoes();
        System.out.println("✅ Avaliação registrada!");
    }
}
