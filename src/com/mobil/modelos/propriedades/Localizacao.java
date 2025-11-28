package com.mobil.modelos.propriedades;
import com.mobil.modelos.pessoas.Usuario;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static double getDistancia(Usuario usuario1, Usuario usuario2) {
        int x1 = usuario1.getLocalizacao().getX();
        int y1 = usuario1.getLocalizacao().getY();
        int x2 = usuario2.getLocalizacao().getX();
        int y2 = usuario2.getLocalizacao().getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }



}
