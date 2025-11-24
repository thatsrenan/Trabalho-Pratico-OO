package com.mobil.modelos.pagamento;

public class PIX extends MetodoDePagamento {
    private String chavePIX;

    public PIX(float dinheiroDisponivel, float precoCorrida, String chavePIX){
        this.setDinheiroDisponivel(dinheiroDisponivel);
        this.setPrecoCorrida(precoCorrida);
        this.chavePIX = chavePIX;
    }

    @Override
    public void pagar() {

    }
}
