package be.kuleuven.tennistoernooijava.Exceptions;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
