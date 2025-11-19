package com.mobil.modelos.propriedades;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int ano;

    public Veiculo(String placa, String marca, String modelo, String cor, int ano) {
        // Construtor com parâmetros
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getAno() {
        return ano;
    }
}
