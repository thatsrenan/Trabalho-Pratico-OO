package com.mobil.excecoes;

public class PagamentoBloqueadoException extends RuntimeException {
    public PagamentoBloqueadoException(String message) {
        super(message);
    }
}