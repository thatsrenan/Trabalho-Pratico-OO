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
            int resposta = Integer.parseInt(respostaStr);

            switch (resposta) {
                case 1 -> {
                    System.out.println("\nEscolha o tipo de corrida:\n" +
                            "1 - Comum\n" +
                            "2 - De Luxo");
                    int tipoCorrida = sc.nextInt();


                    System.out.println("Escolha o método de pagamento que deseja usar:" +
                            "1 - Cartão de Crédito" +
                            "2 - Dinheiro Físico" +
                            "3 - Método de Pagamento" +
                            "4 - PIX");
                    int metodoDePagamentoEscolhido = sc.nextInt();


                    System.out.println("Digite quantos reais você tem disponível no método de pagamento escolhido: ");
                    float dinheiroDisponivel = sc.nextFloat();

                    switch (tipoCorrida) {
                        case 1 -> user.chamarCorrida(motoristas, "Comum", dinheiroDisponivel, 1);
                        case 2 -> user.chamarCorrida(motoristas, "Luxo", dinheiroDisponivel, 1);
                    }
                }
                case 2 -> programaRodando = false;
                default -> System.out.println("Resposta inváida...");
            }
        }



    }
}