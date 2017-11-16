package exceptions;

public class NoSuchRowException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NoSuchRowException";
    }
}
