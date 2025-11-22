package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.MetodoDePagamento;
import com.mobil.modelos.pessoas.Motorista;

import java.util.Scanner;

public abstract class Corrida {
    private String status; // Motorista a caminho, Em andamento, Finalizando
    private Motorista motorista;

    Scanner sc = new Scanner(System.in);

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Motorista getMotorista() {
        return motorista;
    }
}
