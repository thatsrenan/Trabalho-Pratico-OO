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
        this.localizacao = localizacao;
    }

    // Método para definir localização (mantido para compatibilidade)
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    // Método simplificado para chamar corrida (se ainda necessário)
    // A maioria da lógica agora está no PassageiroServico
    public void realizarChamadaBasica() {
        System.out.println("Passageiro " + this.getNome() + " está pronto para chamar uma corrida.");
        System.out.println("Localização atual: [" + this.getLocalizacao().getX() +
                ", " + this.getLocalizacao().getY() + "]");
    }
}