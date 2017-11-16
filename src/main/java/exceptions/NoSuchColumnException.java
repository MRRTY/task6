package exceptions;

public class NoSuchColumnException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NoSuchColumnException";
    }
}
