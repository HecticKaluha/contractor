package exceptions;

public class CouldNotDeleteCustomerException extends Exception
{
    public CouldNotDeleteCustomerException(String message) {
        super(message);
    }
}
