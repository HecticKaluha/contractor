package exceptions;

public class CouldNotFindProductException extends Exception
{
    public CouldNotFindProductException(String message) {
        super(message);
    }
}
