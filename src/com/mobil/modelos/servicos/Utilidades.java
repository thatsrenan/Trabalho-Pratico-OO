package com.mobil.modelos.servicos;

import java.util.Scanner;

public class Utilidades {

    private static Scanner sc = new Scanner(System.in);


    // "Enter" para continuar o programa
    // Serve para não ser uma metralhadora de informação no terminal
    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    // Lê um inteiro escrito no terminal
    public static int lerInteiro() {
        while (true) {
            try {
                System.out.print("> ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }

    public static String lerString() {
        System.out.print("> ");
        String valor = sc.nextLine().trim();
        while (valor.isEmpty()) {
            System.out.println("Entrada inválida! Digite uma string não vazia.");
            System.out.print("> ");
            valor = sc.nextLine().trim();
        }
        return valor;
    }

    public static float lerFloat() {
        while (true) {
            try {
                System.out.print("> ");
                return Float.parseFloat(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número decimal.");
            }
        }
    }

    public static void fecharScanner() {
        if (sc != null) {
            sc.close();
            System.out.println("Scanner fechado com sucesso.");
        }
    }
}
