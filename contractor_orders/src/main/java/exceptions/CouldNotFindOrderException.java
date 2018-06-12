package exceptions;

public class CouldNotFindOrderException extends Exception
{
    public CouldNotFindOrderException(String message) {
        super(message);
    }
}
