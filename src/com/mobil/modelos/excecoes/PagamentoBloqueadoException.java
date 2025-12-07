package com.mobil.modelos.excecoes;

public class PagamentoBloqueadoException extends RuntimeException {
    public PagamentoBloqueadoException(String message) {
        super(message);
    }
}