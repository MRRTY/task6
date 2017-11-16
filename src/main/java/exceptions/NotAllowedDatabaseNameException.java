package exceptions;

public class NotAllowedDatabaseNameException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NotAllowedDatabaseNameException";
    }
}
