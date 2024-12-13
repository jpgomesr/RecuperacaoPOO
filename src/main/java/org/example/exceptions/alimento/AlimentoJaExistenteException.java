package org.example.exceptions.alimento;

public class AlimentoJaExistenteException extends RuntimeException {
    public AlimentoJaExistenteException(String message) {
        super(message);
    }
}
