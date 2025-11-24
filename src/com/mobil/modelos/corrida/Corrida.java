package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.MetodoDePagamento;
import com.mobil.modelos.pessoas.*;

import java.util.Scanner;

public abstract class Corrida {
    private String status; // Motorista a caminho, Em andamento, Finalizando
    private Motorista motorista;
    private Passageiro passageiro;

    Scanner sc = new Scanner(System.in);

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
}
