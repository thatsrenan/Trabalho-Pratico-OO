package com.mobil.modelos.propriedades;

public class Localizacao {
    public static int xMax = 100;
    public static int yMax = 100;

    private int x;
    private int y;

    public Localizacao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
