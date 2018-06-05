package exceptions;

public class CouldNotDeleteProductException extends Exception
{
    public CouldNotDeleteProductException(String message) {
        super(message);
    }
}
