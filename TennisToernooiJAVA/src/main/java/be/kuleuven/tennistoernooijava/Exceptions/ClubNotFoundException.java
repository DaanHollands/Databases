package be.kuleuven.tennistoernooijava.Exceptions;

public class ClubNotFoundException extends RuntimeException {
    public ClubNotFoundException(String message) {
        super(message);
    }
}
