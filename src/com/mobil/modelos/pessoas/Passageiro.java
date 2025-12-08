package com.mobil.modelos.pessoas;

import com.mobil.modelos.propriedades.Localizacao;

public class Passageiro extends Usuario {

    public Passageiro() {

    }

    public Passageiro(String nome, String email, String CPF, String telefone,
                      int senha, Localizacao localizacao) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTelefone(telefone);
        this.setSenha(senha);
        setLocalizacao(localizacao);;
    }
}