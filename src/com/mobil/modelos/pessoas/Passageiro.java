package com.mobil.modelos.pessoas;

import com.mobil.modelos.propriedades.Localizacao;

import java.util.ArrayList;

public class Passageiro extends Usuario{

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

    public static double distancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void chamarCorrida(ArrayList<Motorista> motoristas, String tipoCorrida) {
        System.out.println("Primeiramente, digite sua senha para confimar sua ação: ");
        int senhaEscrita = sc.nextInt();
        if (senhaEscrita != this.getSenha()) {
            return;
        }
        int indiceMotoristaMaisProximo = 0;
        double menorDist = distancia(localizacao.getX(), localizacao.getY(),
                motoristas.get(0).localizacao.getX(), motoristas.get(0).localizacao.getY());

        for (int i = 1; i < motoristas.size(); i++) {
            if (motoristas.get(i).getStatus() == "Disponível") {
                double distAux = distancia(localizacao.getX(), localizacao.getY(),
                        motoristas.get(i).localizacao.getX(), motoristas.get(i).localizacao.getY());
                if (distAux < menorDist) {
                    menorDist = distAux;
                    indiceMotoristaMaisProximo = i;
                }
            }
        }

        // AGORA CRIAR A CORRIDA
    }
}
