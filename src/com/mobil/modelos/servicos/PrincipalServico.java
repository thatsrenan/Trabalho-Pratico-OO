package com.mobil.modelos.servicos;

import com.mobil.modelos.pessoas.Passageiro;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.corrida.Corrida;

import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalServico {
    private Scanner sc;
    private AppServico appServico;
    private PassageiroServico passageiroServico;
    private MotoristaServico motoristaServico;
    private CorridaServico corridaServico;
    private PagamentoServico pagamentoServico;
    private LocalizacaoServico localizacaoServico;

    private Passageiro usuarioLogado;
    private ArrayList<Motorista> motoristas;
    private ArrayList<Corrida> historicoCorridas;

    public PrincipalServico() {
        this.sc = new Scanner(System.in);
        this.appServico = new AppServico();
        this.passageiroServico = new PassageiroServico(sc);
        this.motoristaServico = new MotoristaServico();
        this.corridaServico = new CorridaServico();
        this.pagamentoServico = new PagamentoServico();
        this.localizacaoServico = new LocalizacaoServico();
        this.historicoCorridas = new ArrayList<>();
    }

    // Ciclo de atividades do programa
    public void iniciarSistema() {
        exibirBannerInicial();
        inicializarSistema();
        executarMenuPrincipal();
        encerrarSistema();
    }

    // Baner do Mobil
    private void exibirBannerInicial() {
        System.out.println("\n" +
                "╔══════════════════════════════════════════════════════╗\n" +
                "║                  🚖 MOBIL APP 🚖                     ║\n" +
                "║         Seu aplicativo de transporte premium         ║\n" +
                "╚══════════════════════════════════════════════════════╝\n");
    }

    // Da as condições iniciais do Mobil
    private void inicializarSistema() {
        System.out.println("=== INICIALIZANDO SISTEMA ===");

        // Carregar motoristas
        motoristas = appServico.inicializarMotoristasPadrao();
        System.out.println("✓ " + motoristas.size() + " motoristas carregados");

        // Gerenciar acesso do usuário
        gerenciarAcessoUsuario();

        System.out.println("✓ Sistema inicializado com sucesso!\n");
    }

    // Acesso do usuário ao "login" do Mobil
    // Novo Cadastro é criar seu usuário do ZERO
    // Login rápido é um pré-usuário para facilitar o teste das coisas
    private void gerenciarAcessoUsuario() {
        boolean acessoValido = false;

        while (!acessoValido) {
            System.out.println("\n" +
                    "╔══════════════════════════════════════════╗\n" +
                    "║            ACESSO AO SISTEMA             ║\n" +
                    "╠══════════════════════════════════════════╣\n" +
                    "║ 1 - Novo cadastro                        ║\n" +
                    "║ 2 - Login rápido (usuário de teste)      ║\n" +
                    "║ 3 - Sair                                 ║\n" +
                    "╚══════════════════════════════════════════╝\n" +
                    "\nEscolha uma opção: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    usuarioLogado = passageiroServico.criarNovoPassageiro();
                    acessoValido = true;
                    break;

                case 2:
                    usuarioLogado = criarUsuarioTeste();
                    System.out.println("✓ Login realizado como: " + usuarioLogado.getNome());
                    acessoValido = true;
                    break;

                case 3:
                    System.out.println("Encerrando aplicativo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Usuário pré-feito para testes
    private Passageiro criarUsuarioTeste() {
        return passageiroServico.criarPassageiroComDados(
                "Carlos Silva",
                "carlos.silva@email.com",
                "123.456.789-00",
                "(11) 99999-9999",
                1234,
                50,  // coordenada X
                50   // coordenada Y
        );
    }

    // Menu Principal
    private void executarMenuPrincipal() {
        boolean sistemaAtivo = true;

        while (sistemaAtivo) {
            appServico.limparConsole();
            appServico.exibirMenuPrincipal();

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    // CHAMA A CORRIDA
                    gerenciarChamadaCorrida();
                    break;

                case 2:
                    // MOSTRA AS INFORMAÇÕES DO USUÁRIO
                    passageiroServico.exibirInformacoesDetalhadas(usuarioLogado);
                    pausar();
                    break;

                case 3:
                    // MOSTRA OS MOTORISTAS DISPONÍVEIS
                    exibirMotoristasDisponiveis();
                    pausar();
                    break;

                case 4:
                    exibirHistoricoCorridas();
                    pausar();
                    break;

                case 5:
                    // CONFIGURAÇÃO DAS INFORMAÇÕES DO USUÁRIO
                    gerenciarConfiguracoesUsuario();
                    break;

                case 6:
                    // FECHA O MOBIL
                    sistemaAtivo = confirmarSaida();
                    break;

                default:
                    // Caso default
                    System.out.println("Opção inválida! Tente novamente.");
                    pausar();
            }
        }
    }

    // Chama a corrida
    private void gerenciarChamadaCorrida() {
        appServico.limparConsole();
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║          CHAMAR UMA CORRIDA              ║\n" +
                "╚══════════════════════════════════════════╝\n");

        // 1. Escolher tipo de corrida
        String tipoCorrida = selecionarTipoCorrida();
        if (tipoCorrida == null) return;

        // 2. Escolher método de pagamento
        int metodoPagamento = selecionarMetodoPagamento();
        if (metodoPagamento == -1) return;

        // 3. Informar valor disponível
        float dinheiroDisponivel = informarValorDisponivel(metodoPagamento);
        if (dinheiroDisponivel == -1) return;

        // 4. Chama corrida usando o PassageiroServico
        Corrida corrida = passageiroServico.chamarCorrida(
                usuarioLogado,
                motoristas,
                tipoCorrida,
                dinheiroDisponivel,
                metodoPagamento
        );

        // se der pau, mostra a mensagem
        if (corrida != null) {
            System.out.println("\n✓ Corrida solicitada com sucesso!");
            historicoCorridas.add(corrida);
        } else {
            System.out.println("\n✗ Falha ao solicitar corrida!");
        }

        pausar();
    }

    // Menu de Tipos de Corrida disponíveis no Mobil, recebe a resposta e retorna o tipo escolhido
    private String selecionarTipoCorrida() {
        System.out.println("\n=== TIPO DE CORRIDA ===\n");
        System.out.println("1 - Corrida Comum");
        System.out.println("   • Tarifa base: R$ 5,00");
        System.out.println("   • R$ 1,00 por km");
        System.out.println("\n2 - Corrida de Luxo");
        System.out.println("   • Tarifa base: R$ 9,00");
        System.out.println("   • R$ 2,20 por km");
        System.out.println("\n3 - Voltar");
        System.out.print("\nEscolha: ");

        int opcao = lerInteiro();

        switch (opcao) {
            case 1: return "Comum";
            case 2: return "De Luxo";
            case 3: return null;
            default:
                System.out.println("Opção inválida!");
                // Recursividade pro caso default
                return selecionarTipoCorrida();
        }
    }

    // Menu de Tipos de Métodos de Pagamento disponíveis
    private int selecionarMetodoPagamento() {
        System.out.println("\n=== MÉTODO DE PAGAMENTO ===\n");
        System.out.println("1 - 💵 Dinheiro");
        System.out.println("2 - 📱 PIX");
        System.out.println("3 - 💳 Cartão de Crédito");
        System.out.println("4 - Voltar");
        System.out.print("\nEscolha: ");

        int opcao = lerInteiro();

        if (opcao >= 1 && opcao <= 3) {
            return opcao;
        } else if (opcao == 4) {
            return -1;
        } else {
            System.out.println("Opção inválida!");
            return selecionarMetodoPagamento();
        }
    }

    // Pede o quanto de dinheiro o usuário tem disponível no MetodoDePagamento escolhido
    private float informarValorDisponivel(int metodoPagamento) {
        String[] metodos = {"Dinheiro", "PIX", "Cartão de Crédito"};

        System.out.println("\n=== VALOR DISPONÍVEL ===\n");
        System.out.println("Método selecionado: " + metodos[metodoPagamento - 1]);
        System.out.print("Digite o valor disponível R$ ");

        try {
            float valor = Float.parseFloat(sc.nextLine());

            if (valor <= 0) {
                System.out.println("Valor deve ser maior que zero!");
                return informarValorDisponivel(metodoPagamento);
            }

            return valor;
        } catch (Exception e) {
            System.out.println("Valor inválido! Use números (ex: 50.00)");
            // Recursividade pro caso default
            return informarValorDisponivel(metodoPagamento);
        }
    }

    // Mostra todos motoristas disponíveis
    private void exibirMotoristasDisponiveis() {
        ArrayList<Motorista> disponiveis = motoristaServico.listarMotoristasDisponiveis(motoristas);

        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║       MOTORISTAS DISPONÍVEIS             ║\n" +
                "╚══════════════════════════════════════════╝\n");

        // Checa se há motoristas, e aí sim mostra os motoristas
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum motorista disponível no momento.");
            System.out.println("Tente novamente mais tarde.");
        } else {
            System.out.println("Total: " + disponiveis.size() + " motorista(s) disponível(eis)\n");

            for (int i = 0; i < disponiveis.size(); i++) {
                Motorista m = disponiveis.get(i);
                System.out.println("[" + (i + 1) + "] " + m.getNome());
                System.out.println("   🚗 " + m.getVeiculo().getMarca() + " " +
                        m.getVeiculo().getModelo() + " (" + m.getVeiculo().getCor() + ")");
                System.out.println("   📍 Posição: [" + m.getLocalizacao().getX() +
                        ", " + m.getLocalizacao().getY() + "]");

                float distancia = (float) localizacaoServico.calcularDistanciaEmKM(
                        usuarioLogado.getLocalizacao(), m.getLocalizacao()
                );

                int tempoEstimado = localizacaoServico.estimarTempoDeslocamento(
                        usuarioLogado.getLocalizacao(), m.getLocalizacao()
                );

                System.out.println("   📏 Distância: " + String.format("%.1f", distancia) + " km");
                System.out.println("   ⏱️  Tempo estimado: " + tempoEstimado + " min");
                System.out.println();
            }
        }
    }

    private void exibirHistoricoCorridas() {
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║        HISTÓRICO DE CORRIDAS             ║\n" +
                "╚══════════════════════════════════════════╝\n");

        if (historicoCorridas.isEmpty()) {
            System.out.println("Nenhuma corrida realizada ainda.");
            System.out.println("Chame sua primeira corrida!");
        } else {
            System.out.println("Total de corridas: " + historicoCorridas.size() + "\n");

            for (int i = 0; i < historicoCorridas.size(); i++) {
                Corrida c = historicoCorridas.get(i);
                System.out.println("[" + (i + 1) + "] Motorista: " + c.getMotorista().getNome());
                System.out.println("    Destino: [" + c.getLocalizacaoDestino().getX() +
                        ", " + c.getLocalizacaoDestino().getY() + "]");
                System.out.println("    Veículo: " + c.getMotorista().getVeiculo().getModelo());
                System.out.println();
            }
        }
    }

    // Menu de todas opções de Configuração que o usuário pode mexer, e o leva até a opção escolhida
    private void gerenciarConfiguracoesUsuario() {
        boolean noMenuConfig = true;

        while (noMenuConfig) {
            appServico.limparConsole();
            System.out.println("\n" +
                    "╔══════════════════════════════════════════╗\n" +
                    "║        CONFIGURAÇÕES DO USUÁRIO          ║\n" +
                    "╠══════════════════════════════════════════╣\n" +
                    "║ 1 - Alterar dados cadastrais             ║\n" +
                    "║ 2 - Atualizar localização                ║\n" +
                    "║ 3 - Alterar senha                        ║\n" +
                    "║ 4 - Configurar PIX                       ║\n" +
                    "║ 5 - Voltar ao menu principal             ║\n" +
                    "╚══════════════════════════════════════════╝\n" +
                    "\nEscolha uma opção: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1:

                    passageiroServico.processarMenuPassageiro(usuarioLogado);
                    break;

                case 2:
                    atualizarLocalizacaoUsuario();
                    break;

                case 3:
                    alterarSenhaUsuario();
                    break;

                case 4:
                    configurarChavePix();
                    break;

                case 5:
                    noMenuConfig = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    pausar();
            }
        }
    }

    // Atualiza a localização do usuário com inputs do terminal
    private void atualizarLocalizacaoUsuario() {
        System.out.println("\n=== ATUALIZAR LOCALIZAÇÃO ===");
        System.out.println("Localização atual: [" +
                usuarioLogado.getLocalizacao().getX() + ", " +
                usuarioLogado.getLocalizacao().getY() + "]");

        System.out.print("Nova coordenada X (0-100): ");
        int x = lerInteiro();
        System.out.print("Nova coordenada Y (0-100): ");
        int y = lerInteiro();

        if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
            usuarioLogado.getLocalizacao().setX(x);
            usuarioLogado.getLocalizacao().setY(y);
            System.out.println("✓ Localização atualizada!");
        } else {
            System.out.println("✗ Coordenadas inválidas!");
        }
        pausar();
    }

    // Altera a senha do usuário com inputs do terminal
    private void alterarSenhaUsuario() {
        System.out.println("\n=== ALTERAR SENHA ===");
        System.out.print("Digite a senha atual: ");
        int senhaAtual = sc.nextInt();
        sc.nextLine();

        if (senhaAtual == usuarioLogado.getSenha()) {
            System.out.print("Nova senha (4 dígitos): ");
            int novaSenha = sc.nextInt();
            sc.nextLine();

            if (novaSenha >= 1000 && novaSenha <= 9999) {
                usuarioLogado.setSenha(novaSenha);
                System.out.println("✓ Senha alterada com sucesso!");
            } else {
                System.out.println("✗ Senha deve ter 4 dígitos!");
            }
        } else {
            System.out.println("✗ Senha atual incorreta!");
        }
        pausar();
    }

    private void configurarChavePix() {
        System.out.println("\n=== CONFIGURAR CHAVE PIX ===");
        System.out.print("Digite sua chave PIX (email/telefone/CPF): ");
        String chavePix = sc.nextLine();

        if (!chavePix.trim().isEmpty()) {
            usuarioLogado.setchavePIX(chavePix);
            System.out.println("✓ Chave PIX configurada: " + chavePix);
        } else {
            System.out.println("✗ Chave PIX inválida!");
        }
        pausar();
    }

    //
    private boolean confirmarSaida() {
        System.out.println("\nDeseja realmente sair? (S/N)");
        String confirmacao = sc.nextLine().toUpperCase();
        return confirmacao.equals("S") || confirmacao.equals("SIM");
    }

    // Lê um inteiro escrito no terminal
    private int lerInteiro() {
        while (true) {
            try {
                System.out.print("> ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }

    // "Enter" para continuar o programa
    // Serve para não ser uma metralhadora de informação no terminal
    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    // Menu que aparece após sair do programa
    // Libera o Scanner
    private void encerrarSistema() {
        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║        ENCERRANDO SISTEMA                ║\n" +
                "╚══════════════════════════════════════════╝\n");

        System.out.println("Salvando dados...");
        System.out.println("Desconectando serviços...");

        if (sc != null) {
            sc.close();
        }

        System.out.println("\n" +
                "╔══════════════════════════════════════════╗\n" +
                "║    OBRIGADO POR USAR O MOBIL! 👋        ║\n" +
                "║    Volte sempre!                        ║\n" +
                "╚══════════════════════════════════════════╝\n");
    }
}