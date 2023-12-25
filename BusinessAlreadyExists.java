package ds.graph;

public class BusinessAlreadyExists extends Exception {

    public BusinessAlreadyExists() {
        super();
    }

    public BusinessAlreadyExists(String message) {
        super(message);
    }

    public BusinessAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessAlreadyExists(Throwable cause) {
        super(cause);
    }
}
