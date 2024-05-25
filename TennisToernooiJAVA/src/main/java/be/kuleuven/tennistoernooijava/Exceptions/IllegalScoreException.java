package be.kuleuven.tennistoernooijava.Exceptions;

public class IllegalScoreException extends RuntimeException {
    public IllegalScoreException(String message) {
        super(message);
    }
}
