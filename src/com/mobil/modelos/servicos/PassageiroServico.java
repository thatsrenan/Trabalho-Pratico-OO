package com.mobil.modelos.servicos;

import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.corrida.Corrida;
import com.mobil.modelos.corrida.CorridaComum;
import com.mobil.modelos.corrida.CorridaDeLuxo;
import com.mobil.modelos.propriedades.Localizacao;

import java.util.ArrayList;
import java.util.Scanner;

public class PassageiroServico {
    private Scanner sc;
    private LocalizacaoServico localizacaoServico;

    public PassageiroServico(Scanner scanner) {
        this.sc = scanner;
        this.localizacaoServico = new LocalizacaoServico();
    }

    public Passageiro criarNovoPassageiro() {
        System.out.println("=== CADASTRO DE PASSAGEIRO ===");

        System.out.print("Digite o seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite seu Telefone: ");
        String telefone = sc.nextLine();

        int senha = solicitarSenhaValida();
        Localizacao localizacao = localizacaoServico.solicitarLocalizacaoUsuario(
                "Digite sua posição na cidade (X Y):");

        Passageiro passageiro = new Passageiro();
        passageiro.setNome(nome);
        passageiro.setEmail(email);
        passageiro.setCPF(cpf);
        passageiro.setTelefone(telefone);
        passageiro.setSenha(senha);
        passageiro.setLocalizacao(localizacao);

        System.out.println("✓ Cadastro realizado com sucesso!\n");
        return passageiro;
    }

    public Passageiro criarPassageiroComDados(String nome, String email, String CPF,
                                              String telefone, int senha, int x, int y) {
        Passageiro passageiro = new Passageiro();
        passageiro.setNome(nome);
        passageiro.setEmail(email);
        passageiro.setCPF(CPF);
        passageiro.setTelefone(telefone);
        passageiro.setSenha(senha);
        passageiro.setLocalizacao(localizacaoServico.criarLocalizacao(x, y));
        return passageiro;
    }

    // Senha de no mínimo e máximo 4 dígitos
    private int solicitarSenhaValida() {
        int senha;
        while (true) {
            System.out.print("Digite sua senha (4 dígitos): ");

            while (!sc.hasNextInt()) {
                System.out.println("Por favor, digite apenas números!");
                sc.next();
            }

            senha = sc.nextInt();
            sc.nextLine();

            if (senha >= 1000 && senha <= 9999) {
                return senha;
            }

            System.out.println("Senha inválida! Deve ter exatamente 4 dígitos.");
        }
    }

    public Corrida chamarCorrida(Passageiro passageiro, ArrayList<Motorista> motoristas,
                                 String tipoCorrida, float dinheiroDisponivel, int metodoPagamento) {

        System.out.println("\n=== CHAMAR CORRIDA ===");

        // Verifica senha
        if (!verificarSenha(passageiro)) {
            System.out.println("Senha incorreta! Operação cancelada.");
            return null;
        }

        // Solicita destino da corrida
        Localizacao destino = localizacaoServico.solicitarLocalizacaoUsuario(
                "Digite as coordenadas do DESTINO (0 - 100):");

        // Encontra motorista mais próximo do usuário
        Motorista motorista = encontrarMotoristaMaisProximo(motoristas, passageiro.getLocalizacao());

        if (motorista == null) {
            System.out.println("Nenhum motorista disponível no momento!");
            return null;
        }

        System.out.println("Motorista encontrado: " + motorista.getNome());

        // Cria a corrida escolhida
        if (tipoCorrida.equalsIgnoreCase("Comum")) {
            return new CorridaComum(motorista, dinheiroDisponivel, metodoPagamento,
                    passageiro, destino);
        } else if (tipoCorrida.equalsIgnoreCase("De Luxo") || tipoCorrida.equalsIgnoreCase("Luxo")) {
            return new CorridaDeLuxo(motorista, dinheiroDisponivel, metodoPagamento,
                    passageiro, destino);
        } else {
            System.out.println("Tipo de corrida inválido!");
            return null;
        }
    }

    // Corre o ArrayList em busca do Motorista DISPONÍVEL mais próximo do usuário
    public Motorista encontrarMotoristaMaisProximo(ArrayList<Motorista> motoristas, Localizacao origem) {
        if (motoristas == null || motoristas.isEmpty()) {
            return null;
        }

        Motorista motoristaMaisProximo = null;
        float menorDistancia = Float.MAX_VALUE;

        for (Motorista motorista : motoristas) {
            if ("Disponível".equals(motorista.getStatus())) {
                float distancia = (float) Localizacao.getDistancia(origem, motorista.getLocalizacao());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    motoristaMaisProximo = motorista;
                }
            }
        }

        return motoristaMaisProximo;
    }

    // Verifica a senha para realizar tais ações
    public boolean verificarSenha(Passageiro passageiro) {
        System.out.print("Digite sua senha para prosseguir: ");

        while (!sc.hasNextInt()) {
            System.out.println("Por favor, digite apenas números!");
            sc.next();
        }

        int senhaDigitada = sc.nextInt();
        sc.nextLine();

        return passageiro.getSenha() == senhaDigitada;
    }

    // Praticamente um toString do Passageiro
    public void exibirInformacoesDetalhadas(Passageiro passageiro) {
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║       INFORMAÇÕES DO PASSAGEIRO          ║\n" +
                "╚══════════════════════════════════════════╝");
        System.out.printf("  Nome: %s\n", passageiro.getNome());
        System.out.printf("  Email: %s\n", passageiro.getEmail());
        System.out.printf("  CPF: %s\n", passageiro.getCPF());
        System.out.printf("  Telefone: %s\n", passageiro.getTelefone());
        System.out.printf("  Localização: [%d, %d]\n",
                passageiro.getLocalizacao().getX(),
                passageiro.getLocalizacao().getY());
        System.out.println("╚══════════════════════════════════════════╝\n");
    }

    // Printa o menu de opções do usuário, pega a resposta e o leva pra opção escolhida
    public boolean processarMenuPassageiro(Passageiro passageiro) {
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║          MENU DO PASSAGEIRO              ║\n" +
                "╠══════════════════════════════════════════╣\n" +
                "║ 1 - Ver informações do perfil            ║\n" +
                "║ 2 - Atualizar localização                ║\n" +
                "║ 3 - Alterar dados cadastrais             ║\n" +
                "║ 4 - Voltar ao menu principal             ║\n" +
                "╚══════════════════════════════════════════╝\n" +
                "\nDigite a opção desejada: ");

        int opcao;
        try {
            opcao = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return true;
        }

        switch (opcao) {
            case 1:
                exibirInformacoesDetalhadas(passageiro);
                break;
            case 2:
                atualizarLocalizacaoInterativamente(passageiro);
                break;
            case 3:
                atualizarDadosCadastrais(passageiro);
                break;
            case 4:
                return false;
            default:
                System.out.println("Opção inválida!");
        }

        return true;
    }

    // Atualiza a localizacao do usuário a partir de inputs do terminal
    private void atualizarLocalizacaoInterativamente(Passageiro passageiro) {
        System.out.println("\n=== ATUALIZAR LOCALIZAÇÃO ===");
        Localizacao novaLocalizacao = localizacaoServico.solicitarLocalizacaoUsuario("Digite a nova localização:");
        passageiro.setLocalizacao(novaLocalizacao);
        System.out.println("Localização atualizada com sucesso!");
    }

    // Atualiza os dados do usuários a partir de inputs do terminal
    private void atualizarDadosCadastrais(Passageiro passageiro) {
        System.out.println("\n=== ATUALIZAR DADOS CADASTRAIS ===");

        System.out.print("Novo nome (atual: " + passageiro.getNome() + "): ");
        String novoNome = sc.nextLine();
        if (!novoNome.trim().isEmpty()) {
            passageiro.setNome(novoNome);
        }

        System.out.print("Novo email (atual: " + passageiro.getEmail() + "): ");
        String novoEmail = sc.nextLine();
        if (!novoEmail.trim().isEmpty()) {
            passageiro.setEmail(novoEmail);
        }

        System.out.print("Novo telefone (atual: " + passageiro.getTelefone() + "): ");
        String novoTelefone = sc.nextLine();
        if (!novoTelefone.trim().isEmpty()) {
            passageiro.setTelefone(novoTelefone);
        }

        System.out.println("Dados atualizados com sucesso!");
    }
}