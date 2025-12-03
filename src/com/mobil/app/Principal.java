package com.mobil.app;

import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.pessoas.Passageiro;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cria o passageiro (VOCÊ) que usa o programa
        Passageiro user = new Passageiro();

        // Cria a lista de motoristas e o Carlos
        ArrayList<Motorista> motoristas = new ArrayList<>();
        motoristas.add(new Motorista("Carlos", "carlos.hernqiue@gmail.com", "047.385.198-91", "61 97373-8940", 9898,
                "carlos.hernqiue@gmail.com",50, 80, "GKP3H12", "Fiat", "Uno", "Vermelho", 2016, "04123456789", 2028));

        boolean programaRodando = true;

        // mPagamento: 1 = Dinheiro, 2 = PIX,, 3 = CartaoDeCredito
        while(programaRodando) {
            System.out.println("""
                    
                    MOBIL
                    Digite a ação desejada:
                    1 - Chamar corrida
                    2 - Sair""");

            //Evitar de ler o \n e entrar em loop
            String respostaStr = sc.nextLine();
            int resposta;

            // Exception String vazia
            while (respostaStr.trim().isEmpty()) {
                System.out.println("Entrada inválida. Digite um número:");
                respostaStr = sc.nextLine();
            }

            try {
                resposta = Integer.parseInt(respostaStr);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                continue; // Volta para o início do loop
            }

            switch (resposta) {
                case 1 -> {
                    int tipoCorrida = 0;
                    int metodoDePagamentoEscolhido = 0;

                    boolean respostaAceita = false;
                    while(!respostaAceita) {
                        System.out.println("\nEscolha o tipo de corrida:\n" +
                                "1 - Comum\n" +
                                "2 - De Luxo");
                        tipoCorrida = Integer.parseInt(sc.nextLine());
                        if (tipoCorrida != 1 && tipoCorrida != 2) {
                            System.out.println("Opção inexistente.");
                        } else {
                            respostaAceita = true;
                        }
                    }

                    respostaAceita = false;
                    while(!respostaAceita) {
                        System.out.println("\nEscolha o método de pagamento que deseja usar:\n" +
                                "1 - Dinheiro Físico\n" +
                                "2 - PIX\n" +
                                "3 - Cartão de Crédito");
                        metodoDePagamentoEscolhido = Integer.parseInt(sc.nextLine());
                        if (metodoDePagamentoEscolhido != 1 && metodoDePagamentoEscolhido != 2 && metodoDePagamentoEscolhido != 3) {
                            System.out.println("Opção inexistente.");
                        } else {
                            respostaAceita = true;
                        }
                    }


                    System.out.println("\nDigite quantos reais você tem disponível no método de pagamento escolhido: ");
                    float dinheiroDisponivel = Float.parseFloat(sc.nextLine());

                    switch (tipoCorrida) {
                        case 1 -> user.chamarCorrida(motoristas, "Comum", dinheiroDisponivel, metodoDePagamentoEscolhido);
                        case 2 -> user.chamarCorrida(motoristas, "De Luxo", dinheiroDisponivel, metodoDePagamentoEscolhido);
                    }
                }

                case 2 -> programaRodando = false;
                default -> System.out.println("Opção inexistente.");
            }
        }



    }
}