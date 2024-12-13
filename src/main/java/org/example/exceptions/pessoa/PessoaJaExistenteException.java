package org.example.exceptions.pessoa;

public class PessoaJaExistenteException extends RuntimeException {
    public PessoaJaExistenteException(String message) {
        super(message);
    }
}
