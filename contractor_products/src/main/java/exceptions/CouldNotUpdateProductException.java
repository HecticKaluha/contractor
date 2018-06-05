package exceptions;

public class CouldNotUpdateProductException extends Exception
{
    public CouldNotUpdateProductException(String message) {
        super(message);
    }
}
