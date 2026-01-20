package pl.koder95.rickandmortyfx.api;

public class InvalidConnectionException extends RuntimeException {
    public InvalidConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
