package exception;

public class BrowserNorSupportedException extends Exception {

    public BrowserNorSupportedException(String browseName) {
        super(String.format("Browser %s is not supported"));
    }
}
