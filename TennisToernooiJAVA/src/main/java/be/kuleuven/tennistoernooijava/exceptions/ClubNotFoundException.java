package be.kuleuven.tennistoernooijava.exceptions;

public class ClubNotFoundException extends RuntimeException {
    public ClubNotFoundException(String message) {
        super(message);
    }
}
