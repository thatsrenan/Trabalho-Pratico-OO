package com.mobil.app;

import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        // Cria o passageiro que usa o programa
        Passageiro user = new Passageiro();

        // Cria a lista de motoristas e o Carlos
        ArrayList<Motorista> motoristas = new ArrayList<>();
        motoristas.add(new Motorista("Carlos", "carlos.hernqiue@gmail.com", "047.385.198-91", "61 97373-8940", 9898,
                50, 80, "GKP3H12", "Fiat", "Uno", "Vermelho", 2016, "04123456789", 2028));

        user.chamarCorrida(motoristas, "Comum");

    }
}