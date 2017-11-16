package exceptions;

public class NoSuchDatabaseException extends DatabaseException {
    @Override
    public String getMessage() {
        return "NoSuchDatabaseException";
    }
}

