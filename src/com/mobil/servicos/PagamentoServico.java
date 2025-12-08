package com.mobil.servicos;

import com.mobil.modelos.pagamento.MetodoDePagamento;
import com.mobil.modelos.pagamento.Dinheiro;
import com.mobil.modelos.pagamento.PIX;
import com.mobil.modelos.pagamento.CartaoDeCredito;
import com.mobil.modelos.pessoas.Passageiro;

public class PagamentoServico {

    public MetodoDePagamento criarMetodoPagamento(int tipoPagamento,
                                                  float dinheiroDisponivel,
                                                  float precoCorrida,
                                                  Passageiro passageiro) {
        switch (tipoPagamento) {
            case 1:
                return new Dinheiro(dinheiroDisponivel, precoCorrida, passageiro);
            case 2:
                return new PIX(dinheiroDisponivel, precoCorrida, passageiro);
            case 3:
                return new CartaoDeCredito(dinheiroDisponivel, precoCorrida, passageiro);
            default:
                throw new IllegalArgumentException("Método de pagamento inválido: " + tipoPagamento);
        }
    }

}