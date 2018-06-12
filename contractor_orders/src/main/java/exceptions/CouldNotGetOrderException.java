package exceptions;

public class CouldNotGetOrderException extends Exception
{
    public CouldNotGetOrderException(String message) {
        super(message);
    }
}
