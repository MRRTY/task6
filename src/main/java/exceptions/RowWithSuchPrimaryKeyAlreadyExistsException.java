package exceptions;

public class RowWithSuchPrimaryKeyAlreadyExistsException extends DatabaseException {
    @Override
    public String getMessage() {
        return "RowWithSuchPrimaryKeyAlreadyExistsException";
    }
}
