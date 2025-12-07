package com.mobil.modelos.propriedades;

import java.util.Scanner;

public class Avaliacao {
    private int totalDeAvaliacoes;
    private float mediaAvaliacoes;
    private int somaDasAvaliacoes;


    // SEMPRE DE 0 a 5!
    // Construtor do zero
    public Avaliacao() {
        totalDeAvaliacoes = 0;
        mediaAvaliacoes = 0;
        somaDasAvaliacoes = 0;
    }

    public Avaliacao(int totalDeAvaliacoes, int somaDasAvaliacoes) {
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.somaDasAvaliacoes = somaDasAvaliacoes;
        mediaAvaliacoes = (float) somaDasAvaliacoes / totalDeAvaliacoes;
    }

    public void somaUmaAvaliacao() {
        this.somaDasAvaliacoes++;
        this.totalDeAvaliacoes++;
    }

    public void calcularMediaAvaliacoes(){
        mediaAvaliacoes = (float) somaDasAvaliacoes / totalDeAvaliacoes;
    }

    public int getSomaDasAvaliacoes() {
        return somaDasAvaliacoes;
    }

    public float getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
}

