package com.mobil.modelos.pessoas;
import com.mobil.modelos.propriedades.*;
import com.mobil.modelos.servicos.*;

public class Motorista extends Usuario{
    private String status; // Em andamento, Finalizada, etc
    private CNH CNH;
    private Veiculo veiculo;

    public Motorista(String nome, String email, String CPF, String telefone, int senha, int x, int y,
                     String placa, String marca, String modelo, String cor, int ano, String numeroCNH, int anoDeValidade, int totalDeAvaliacoes, int somaDasAvaliacoes) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTelefone(telefone);
        this.setSenha(senha);
        localizacao = LocalizacaoServico.criarLocalizacao(x, y);
        veiculo = new Veiculo(placa, marca, modelo, cor, ano);
        CNH = new CNH(numeroCNH, anoDeValidade);
        avaliacao = new Avaliacao( totalDeAvaliacoes,  somaDasAvaliacoes);
        status = "Disponível";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Avaliacao getAvaliacao(){
        return avaliacao;
    }
}
