package org.example.exceptions.pet;

public class PetJaExistenteException extends RuntimeException {
    public PetJaExistenteException(String message) {
        super(message);
    }
}
