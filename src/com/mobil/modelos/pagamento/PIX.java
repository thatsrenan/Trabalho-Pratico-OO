package com.mobil.modelos.pagamento;

public class PIX extends MetodoDePagamento {
    private String chavePIX;

    public PIX(float dinheiroDisponivel, float precoCorrida){
        this.setDinheiroDisponivel(dinheiroDisponivel);
        this.setPrecoCorrida(precoCorrida);
    }

    @Override
    public void pagar() {
        if (getDinheiroDisponivel() < getPrecoCorrida()) {
            // Exceção
        } else {
            System.out.println("Você tem o dinheiro disponível, digite sua senha para confirmar o PIX: \n");

            // IMPLEMENTAR
        }
    }
}
