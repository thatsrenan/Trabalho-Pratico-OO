package com.mobil.modelos.pessoas;

import com.mobil.modelos.corrida.Corrida;
import com.mobil.modelos.pessoas.Motorista;
import com.mobil.modelos.corrida.CorridaComum;
import com.mobil.modelos.propriedades.Localizacao;

import java.util.ArrayList;

public class Passageiro extends Usuario{

    private Corrida corrida;

    // Não usa parâmetros, pergunta os dados, porque é para ser preenchido pelo Passageiro Usuário
    public Passageiro() {
        System.out.println("Digite o seu nome: ");
        this.setNome(sc.nextLine());

        System.out.println("Digite seu email: ");
        this.setEmail(sc.nextLine());

        System.out.println("Digite seu CPF: ");
        this.setCPF(sc.nextLine());

        System.out.println("Digite seu Telefone: ");
        this.setTelefone(sc.nextLine());

        System.out.println("Digite sua senha(4 dígitos): ");
        this.setSenha(sc.nextInt());

        // EXCEÇÃO
        while(getSenha() < 1000 || getSenha() > 9999) {
            System.out.println("Digite uma senha válida (4 dígitos): ");
            this.setSenha(sc.nextInt());
        }

        System.out.println("Digite as coordenadas de sua posição na cidade.\nMínimo 0 - Máximo 100\nX: ");
        int x = sc.nextInt();

        // EXCEÇÃO do X
        while(x < 0 || x > localizacao.xMax) {
            System.out.println("Coordenada X inválida.");
            x = sc.nextInt();
        }

        System.out.println("Y: ");
        int y = sc.nextInt();

        // EXCEÇÃO do Y
        while(y < 0 || y > localizacao.yMax) {
            System.out.println("Coordenada Y inválida.");
            y = sc.nextInt();
        }

        localizacao = new Localizacao(x, y);
    }

    public void chamarCorrida(ArrayList<Motorista> motoristas, String tipoCorrida, float dinheiroDisponivel, int mPagamento) {
        System.out.println("Digite sua senha para confimar sua ação: ");
        int senhaEscrita = sc.nextInt();
        if (senhaEscrita != this.getSenha()) {
            return;
        }

        System.out.println("Digite as coordenadas do DESTINO (0 - 100):");
        System.out.println("X: ");
        int destX = sc.nextInt();
        System.out.println("Y: ");
        int destY = sc.nextInt();

        // EXCEÇÃO do X
        while(destX < 0 || destX > localizacao.xMax) {
            System.out.println("Coordenada X inválida. Digite outra localização:");
            destX = sc.nextInt();
        }

        // EXCEÇÃO do Y
        while(destY < 0 || destY > localizacao.yMax) {
            System.out.println("Coordenada Y inválida. Digite outra localização:");
            destY = sc.nextInt();
        }

        Localizacao destino = new Localizacao(destX, destY);

        int indiceMotoristaMaisProximo = 0;
        float menorDist = (float) Localizacao.getDistancia(this.getLocalizacao(), motoristas.get(0).getLocalizacao());
        indiceMotoristaMaisProximo = 0;

        for (int i = 1; i < motoristas.size(); i++) {
            if (motoristas.get(i).getStatus().equals("Disponível")) {
                float distAux = (float) Localizacao.getDistancia(this.getLocalizacao(), motoristas.get(i).getLocalizacao());
                if (distAux < menorDist) {
                    menorDist = distAux;
                    indiceMotoristaMaisProximo = i;
                }
            }
        }

        if (tipoCorrida == "Comum") {
            corrida = new CorridaComum(indiceMotoristaMaisProximo, motoristas, dinheiroDisponivel, menorDist, mPagamento, this, destino);
            System.out.println("Corrida comum criada.");
        } else if (tipoCorrida == "De Luxo") {
            // criar corrida de luxo
        }

    }
}
