package com.mobil.modelos.pessoas;

import com.mobil.modelos.propriedades.*;

public abstract class Usuario {
    private String nome;
    private String email;
    private String CPF;
    private String telefone;
    private int senha;

    Localizacao localizacao;
    protected Avaliacao avaliacao;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getSenha() {
        return senha;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

}