package org.example.exceptions.alimento;

public class AlimentoNaoEncontradoException extends RuntimeException {
    public AlimentoNaoEncontradoException(String message) {
        super(message);
    }
}
