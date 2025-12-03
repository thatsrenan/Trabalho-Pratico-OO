package com.mobil.modelos.pagamento;

import com.mobil.modelos.pessoas.Passageiro;

public class CartaoDeCredito extends MetodoDePagamento {


    public CartaoDeCredito(float dinheiroDisponivel, float precoCorrida, Passageiro passageiro) {
        super(dinheiroDisponivel, precoCorrida, passageiro);
    }

    @Override
    public void pagar() {
        System.out.println("PAGAMENTO POR CARTÃO DE CRÉDITO\n");

        if (getDinheiroDisponivel() < getPrecoCorrida()) {
            // Exceção
        } else {
            System.out.println("Você tem o dinheiro disponível, digite sua senha para confirmar a transação: \n");

            for (int i = 0; i < 3; i++) {
                int resposta = sc.nextInt();
                if (resposta != this.passageiro.getSenha()) {
                    if (i == 2) {
                        System.out.println("Cartão bloqueado.");
                        // EXCEÇÃO
                    } else {
                        System.out.printf("Senha errada. Você tem mais %d tentativas!\n", 2 - i);

                    }
                } else {
                    System.out.println("Corrida devidamente paga.");
                }
            }
        }
    }
}
