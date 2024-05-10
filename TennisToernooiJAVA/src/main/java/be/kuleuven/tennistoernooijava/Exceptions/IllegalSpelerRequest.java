package be.kuleuven.tennistoernooijava.Exceptions;

public class IllegalSpelerRequest extends IllegalArgumentException{
    public IllegalSpelerRequest(String message) {
        super(message);
    }
}
