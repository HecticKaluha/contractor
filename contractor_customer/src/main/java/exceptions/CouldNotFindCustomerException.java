package exceptions;

public class CouldNotFindCustomerException extends Exception
{
    public CouldNotFindCustomerException(String message) {
        super(message);
    }
}
