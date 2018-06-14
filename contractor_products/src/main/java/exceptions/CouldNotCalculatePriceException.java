package exceptions;

public class CouldNotCalculatePriceException extends Exception
{
    public CouldNotCalculatePriceException(String message) {
        super(message);
    }
}
