package org.example.exceptions.pet;

public class PetNaoEncontradoException extends RuntimeException {
    public PetNaoEncontradoException(String message) {
        super(message);
    }
}
