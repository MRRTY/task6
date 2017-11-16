package exceptions;

public class InvalidTypeException extends DatabaseException {
    @Override
    public String getMessage() {
        return "InvalidTypeException";
    }
}
