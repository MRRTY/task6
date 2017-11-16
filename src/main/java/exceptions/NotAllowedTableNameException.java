package exceptions;

public class NotAllowedTableNameException extends DatabaseException{
    @Override
    public String getMessage() {
        return "NotAllowedTableNameException";
    }
}
