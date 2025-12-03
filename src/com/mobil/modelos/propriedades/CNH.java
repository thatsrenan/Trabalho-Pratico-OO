package com.mobil.modelos.propriedades;

public class CNH {
    private static int anoAtual = 2025;
    private String numeroCNH; // 9 dígitos
    private int anoDeValidade;

    public CNH(String numeroCNH, int anoDeValidade) {
        this.numeroCNH = numeroCNH;
        this.anoDeValidade = anoDeValidade;
    }

    public boolean CNHehValida() {
        return (anoDeValidade >= anoAtual);
    }
}
