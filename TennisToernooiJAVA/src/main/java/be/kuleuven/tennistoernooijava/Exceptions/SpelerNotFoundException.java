package be.kuleuven.tennistoernooijava.Exceptions;

public class SpelerNotFoundException extends RuntimeException {
    public SpelerNotFoundException(String message) {
        super(message);
    }
}
