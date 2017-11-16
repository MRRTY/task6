package exceptions;

public class InvalidTableException extends DatabaseException {
    @Override
    public String getMessage() {
        return "InvalidTableException";
    }
}
