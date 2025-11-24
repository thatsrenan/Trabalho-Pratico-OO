package com.mobil.modelos.pagamento;

public class CartaoDeCredito extends MetodoDePagamento {


    public CartaoDeCredito(float dinheiroDisponivel, float precoCorrida) {
        this.setDinheiroDisponivel(dinheiroDisponivel);
        this.setPrecoCorrida(precoCorrida);
    }

    @Override
    public void pagar() {

    }
}
