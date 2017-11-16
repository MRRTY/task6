package exceptions;

public class NoSuchTableException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NoSuchTableException";
    }
}
