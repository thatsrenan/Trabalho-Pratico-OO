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

}
