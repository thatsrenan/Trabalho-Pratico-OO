package com.mobil.servicos;

import com.mobil.modelos.pessoas.Motorista;
import java.util.ArrayList;

public class AppServico {

    public ArrayList<Motorista> inicializarMotoristasPadrao() {
        ArrayList<Motorista> motoristas = new ArrayList<>();

        motoristas.add(new Motorista("Carlos", "carlos.hernqiue@gmail.com", "047.385.198-91",
                "61 97373-8940", 9898,50, 80, "GKP3H12", "Fiat", "Uno",
                "Vermelho", 2016, "04123456789", 2028, 150, 684));

        motoristas.add(new Motorista("Jorge", "jorginho.alves@hotmail.com", "761.276.123-09",
                "60 98475-3222", 0002, 10, 15, "ATR7N18", "Hyundai", "HB20",
                "Prata", 2020, "83718273842", 2030, 150, 706));

        motoristas.add(new Motorista("Armando", "armando.nunes@gmail.com", "382.732.743-02",
                "21 97452-1653", 2020, 90, 8, "TRK9E00", "Honda", "Onix",
                "Azul", 2018, "93489472983", 2027, 150, 563));

        motoristas.add(new Motorista("Fernanda", "fernanda.silva@outlook.com", "123.456.789-00",
                "11 98765-4321", 3333, 50, 50,
                "ABC1D23", "Toyota", "Corolla", "Branco", 2022, "11223344556", 2032, 150, 648));

        motoristas.add(new Motorista("Roberto", "roberto.oliveira@gmail.com", "987.654.321-00",
                "31 99876-5432", 4444,85, 90,
                "XYZ9K88", "Volkswagen", "Gol", "Preto", 2019, "99887766554", 2029, 150, 699));

        motoristas.add(new Motorista("Carla", "carla.santos@yahoo.com", "555.666.777-88",
                "41 97654-3210", 5555, 15, 85,
                "MNO5P67", "Chevrolet", "Onix", "Vermelho", 2021, "66778899001", 2031, 150, 730));

        motoristas.add(new Motorista("Pedro", "pedro.almeida@hotmail.com", "111.222.333-44",
                "81 99234-5678", 6666,25, 60,
                "QRS8T90", "Renault", "Kwid", "Cinza", 2020, "22334455667", 2030, 150, 720));

        motoristas.add(new Motorista("Mariana", "mariana.lima@gmail.com", "777.888.999-00",
                "71 98543-2109", 7777,75, 40,
                "UVW2X34", "Ford", "Ka", "Verde", 2017, "33445566778", 2026, 150, 610));

        return motoristas;
    }

    public void exibirMenuPrincipal() {
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║              MENU PRINCIPAL              ║\n" +
                "╠══════════════════════════════════════════╣\n" +
                "║ 1 - 🚖 Chamar corrida                    ║\n" +
                "║ 2 - 👤 Ver informações do perfil         ║\n" +
                "║ 3 - 🚗 Ver motoristas disponíveis        ║\n" +
                "║ 4 - 📜 Histórico de corridas             ║\n" +
                "║ 5 - ⚙️  Configurações                    ║\n" +
                "║ 6 - 🚪 Sair                              ║\n" +
                "╚══════════════════════════════════════════╝\n" +
                "\nDigite a opção desejada: ");
    }

    // Limpa o console
    public void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Caso default, imprime várias linhas vazias (parece que apagou o console)
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}