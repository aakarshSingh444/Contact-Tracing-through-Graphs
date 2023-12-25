package ds.graph;

public class BusinessDoesNotExist extends Exception {

    public BusinessDoesNotExist() {
        super();
    }

    public BusinessDoesNotExist(String message) {
        super(message);
    }

    public BusinessDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessDoesNotExist(Throwable cause) {
        super(cause);
    }
}
