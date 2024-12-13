package org.example.exceptions.pessoa;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String message) {
        super(message);
    }
}
