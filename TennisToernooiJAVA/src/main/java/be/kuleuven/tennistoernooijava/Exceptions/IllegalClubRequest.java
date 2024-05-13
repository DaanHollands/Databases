package be.kuleuven.tennistoernooijava.Exceptions;

public class IllegalClubRequest extends IllegalArgumentException{
    public IllegalClubRequest(String message) {
        super(message);
    }
}
