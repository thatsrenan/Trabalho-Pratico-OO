package com.mobil.modelos.corrida;

import com.mobil.modelos.pagamento.MetodoDePagamento;
import com.mobil.modelos.pessoas.*;
import com.mobil.modelos.propriedades.Localizacao;

import java.util.Scanner;

public abstract class Corrida {
    private String status; // Motorista a caminho, Em andamento, Finalizando
    private Motorista motorista;
    private Passageiro passageiro;
    private Localizacao localizacao;
    private Localizacao localizacaoDestino;

    Scanner sc = new Scanner(System.in);

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Localizacao getLocalizacao(){
        return localizacao;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
}
