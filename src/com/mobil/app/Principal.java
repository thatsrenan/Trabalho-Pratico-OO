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

        // Cria a lista de motoristas e 8 motoristas.
        ArrayList<Motorista> motoristas = new ArrayList<>();
        motoristas.add(new Motorista("Carlos", "carlos.hernqiue@gmail.com", "047.385.198-91",
                "61 97373-8940", 9898,50, 80, "GKP3H12", "Fiat", "Uno",
                "Vermelho", 2016, "04123456789", 2028));

        motoristas.add(new Motorista("Jorge", "jorginho.alves@hotmail.com", "761.276.123-09",
                "60 98475-3222", 0002, 10, 15, "ATR7N18", "Hyundai", "HB20",
                "Prata", 2020, "83718273842", 2030));

        motoristas.add(new Motorista("Armando", "armando.nunes@gmail.com", "382.732.743-02",
                "21 97452-1653", 2020, 90, 8, "TRK9E00", "Honda", "Onix",
                "Azul", 2018, "93489472983", 2027));

        motoristas.add(new Motorista("Fernanda", "fernanda.silva@outlook.com", "123.456.789-00",
                "11 98765-4321", 3333, 50, 50,
                "ABC1D23", "Toyota", "Corolla", "Branco", 2022, "11223344556", 2032));

        motoristas.add(new Motorista("Roberto", "roberto.oliveira@gmail.com", "987.654.321-00",
                "31 99876-5432", 4444,85, 90,
                "XYZ9K88", "Volkswagen", "Gol", "Preto", 2019, "99887766554", 2029));

        motoristas.add(new Motorista("Carla", "carla.santos@yahoo.com", "555.666.777-88",
                "41 97654-3210", 5555, 15, 85,
                "MNO5P67", "Chevrolet", "Onix", "Vermelho", 2021, "66778899001", 2031));

        motoristas.add(new Motorista("Pedro", "pedro.almeida@hotmail.com", "111.222.333-44",
                "81 99234-5678", 6666,25, 60,
                "QRS8T90", "Renault", "Kwid", "Cinza", 2020, "22334455667", 2030));

        motoristas.add(new Motorista("Mariana", "mariana.lima@gmail.com", "777.888.999-00",
                "71 98543-2109", 7777,75, 40,
                "UVW2X34", "Ford", "Ka", "Verde", 2017, "33445566778", 2026));


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