package com.mobil.modelos.pagamento;

import com.mobil.modelos.excecoes.*;
import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.servicos.Utilidades;

public class PIX extends MetodoDePagamento {

    public PIX(float dinheiroDisponivel, float precoCorrida, Passageiro passageiro){
        super(dinheiroDisponivel, precoCorrida, passageiro);
    }

    @Override
    public void pagar() {
        System.out.println("\nPAGAMENTO POR PIX");

        if (getDinheiroDisponivel() < getPrecoCorrida()) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente no PIX! Disponível: R$ " +
                            getDinheiroDisponivel() + " | Necessário: R$ " + getPrecoCorrida()
            );
        } else {
            System.out.println("Você tem o dinheiro disponível, digite sua senha para confirmar o PIX: ");

            for (int i = 0; i < 3; i++) {
                int resposta = Utilidades.lerInteiro();
                if (resposta != this.passageiro.getSenha()) {
                    if (i == 2) {
                        System.out.println("Seu pix foi bloqueado pelo banco.");
                        throw new PagamentoBloqueadoException(
                                "PIX bloqueado após 3 tentativas incorretas."
                        );
                    } else {
                        System.out.printf("Senha errada. Você tem mais %d tentativas!\n", 2 - i);

                    }
                } else {
                    System.out.println("Corrida devidamente paga.");
                    break;
                }
            }
        }
    }
}
