package be.kuleuven.tennistoernooijava.Exceptions;

public class EmptyInputException extends IllegalArgumentException {
    public EmptyInputException(String message) {
        super(message);
    }
}
