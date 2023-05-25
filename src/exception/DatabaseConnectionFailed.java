package exception;

public class DatabaseConnectionFailed extends RuntimeException {
    public DatabaseConnectionFailed(String errMsg) {
        super(errMsg);
    }
}
