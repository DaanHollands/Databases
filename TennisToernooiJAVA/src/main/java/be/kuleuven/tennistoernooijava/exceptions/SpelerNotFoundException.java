package be.kuleuven.tennistoernooijava.exceptions;

public class SpelerNotFoundException extends RuntimeException {
    public SpelerNotFoundException(String message) {
        super(message);
    }
}
