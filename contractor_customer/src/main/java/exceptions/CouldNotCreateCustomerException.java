package exceptions;

public class CouldNotCreateCustomerException extends Exception
{
    public CouldNotCreateCustomerException(String message) {
        super(message);
    }
}
