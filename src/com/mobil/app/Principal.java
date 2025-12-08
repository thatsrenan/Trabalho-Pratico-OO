package com.mobil.app;

import com.mobil.servicos.PrincipalServico;

public class Principal {
    public static void main(String[] args) {
        try {
            PrincipalServico principalServico = new PrincipalServico();
            principalServico.iniciarSistema();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}