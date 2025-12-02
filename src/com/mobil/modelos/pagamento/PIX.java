package com.mobil.modelos.pagamento;

public class PIX extends MetodoDePagamento {
    private String chavePIX;

    public PIX(float dinheiroDisponivel, float precoCorrida){
        this.setDinheiroDisponivel(dinheiroDisponivel);
        this.setPrecoCorrida(precoCorrida);
    }

    @Override
    public void pagar() {

    }
}
