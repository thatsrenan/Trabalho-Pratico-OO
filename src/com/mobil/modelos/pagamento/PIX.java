package com.mobil.modelos.pagamento;

import com.mobil.modelos.pessoas.Passageiro;

public class PIX extends MetodoDePagamento {
    private String chavePIX;

    public PIX(float dinheiroDisponivel, float precoCorrida, Passageiro passageiro){
        super(dinheiroDisponivel, precoCorrida, passageiro);
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
