package com.mobil.modelos.pagamento;

import com.mobil.modelos.excecoes.SaldoInsuficienteException;
import com.mobil.modelos.pessoas.Passageiro;

public class Dinheiro extends MetodoDePagamento{

    public Dinheiro(float dinheiroDisponivel, float precoCorrida, Passageiro passageiro) {
        super(dinheiroDisponivel, precoCorrida, passageiro);
    }

    @Override // pagar é no final da Corrida
    public void pagar() {
        System.out.println("\nPAGAMENTO POR DINHEIRO FÍSICO");

        if (this.getDinheiroDisponivel() < this.getPrecoCorrida()) {

            throw new SaldoInsuficienteException(
                    "Saldo insuficiente! Disponível: R$ " +
                            getDinheiroDisponivel() + " | Necessário: R$ " + getPrecoCorrida()
            );

        } else {
            int[] notas = {200, 100, 50, 20, 10, 5, 2, 1};
            int[] qtd = new int[notas.length];

            float troco = this.getDinheiroDisponivel() - this.getPrecoCorrida();
            boolean moedinhas = false;

            if (troco > 0) {

                for (int i = 0; i < notas.length; i++) {
                    if (troco >= notas[i]) {
                        qtd[i] = (int)(troco / notas[i]); // quantas notas cabem
                        troco -= qtd[i] * notas[i];       // subtrai do troco
                    }
                }

                // Se sobrou valor fracionado, é "moedinha"
                if (troco > 0 && troco < 1) {
                    moedinhas = true;
                }

                System.out.println("Corrida devidamente paga.");
                System.out.println("Você recebeu troco do motorista!");

                for (int i = 0; i < notas.length; i++) {
                    if (i == notas.length - 1) {
                        System.out.println(qtd[i] + " moeda(s) de " + notas[i]);
                    } else if (qtd[i] > 0) {
                        System.out.println(qtd[i] + " nota(s) de " + notas[i]);
                    }
                }

                if (moedinhas) {
                    System.out.println("Sobrou um troquinho em moedas: R$ " + String.format("%.2f", troco));
                }

            } else {
                System.out.println("Corrida devidamente paga.");
                System.out.println("Você não recebeu troco.");
            }
        }
    }
}
