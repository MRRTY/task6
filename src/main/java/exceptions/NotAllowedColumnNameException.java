package exceptions;

public class NotAllowedColumnNameException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NotAllowedColumnNameException";
    }
}
