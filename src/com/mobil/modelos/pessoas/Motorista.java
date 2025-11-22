package com.mobil.modelos.pessoas;
import com.mobil.modelos.propriedades.*;

public class Motorista extends Usuario{
    public String chavePIX;
    private String status; // Em andamento, Finalizada, etc
    private CNH CNH;
    private Veiculo veiculo;

    public Motorista(String nome, String email, String CPF, String telefone, int senha, String chavePIX, int x, int y,
                     String placa, String marca, String modelo, String cor, int ano, String numeroCNH, int anoDeValidade) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTelefone(telefone);
        this.setSenha(senha);
        this.setchavePIX(chavePIX);
        localizacao = new Localizacao(x, y);
        veiculo = new Veiculo(placa, marca, modelo, cor, ano);
        CNH = new CNH(numeroCNH, anoDeValidade);
        status = "Disponível";
    }

    public String getStatus() {
        return status;
    }
}
