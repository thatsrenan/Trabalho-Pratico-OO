package com.mobil.modelos.pagamento;

public abstract class MetodoDePagamento {
    private float dinheiroDisponivel; // de quem chamou a corrida
    private float precoCorrida;

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

    public void setPrecoCorrida(float precoCorrida) {
        this.precoCorrida = precoCorrida;
    }
}
