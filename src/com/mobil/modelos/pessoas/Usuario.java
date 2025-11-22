package com.mobil.modelos.pessoas;

import com.mobil.modelos.propriedades.*;

import java.util.Scanner;

public abstract class Usuario {
    private String nome;
    private String email;
    private String CPF;
    private String telefone;
    private int senha;
    private String chavePIX;

    Localizacao localizacao;
    Avaliacao avaliacao;

    Scanner sc = new Scanner(System.in);

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

    public void setchavePIX(String chavePIX){
        this.chavePIX = chavePIX;
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

    public String getchavePIX(){
        return this.chavePIX;
    }

}