package com.mobil.modelos.pagamento;

import com.mobil.modelos.pessoas.Passageiro;

public abstract class MetodoDePagamento {
    private float dinheiroDisponivel; // de quem chamou a corrida
    private float precoCorrida;
    Passageiro passageiro;

    public MetodoDePagamento(float dinheiroDisponivel, float precoCorrida, Passageiro passageiro){
        this.dinheiroDisponivel = dinheiroDisponivel;
        this.precoCorrida = precoCorrida;
        this.passageiro = passageiro;
    }

    public abstract void pagar();

    public float getDinheiroDisponivel() {
        return dinheiroDisponivel;
    }

    public float getPrecoCorrida() {
        return precoCorrida;
    }

    public void setDinheiroDisponivel(float dinheiroDisponivel) {
        this.dinheiroDisponivel = dinheiroDisponivel;
    }

}
