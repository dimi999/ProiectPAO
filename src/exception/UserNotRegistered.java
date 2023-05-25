package exception;

public class UserNotRegistered extends Exception {
    public UserNotRegistered(String errMsg) {
        super(errMsg);
    }
}
