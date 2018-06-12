package exceptions;

public class CouldNotDeleteOrderException extends Exception
{
    public CouldNotDeleteOrderException(String message) {
        super(message);
    }
}
