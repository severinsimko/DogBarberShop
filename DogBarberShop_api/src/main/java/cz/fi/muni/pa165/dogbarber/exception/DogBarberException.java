package cz.fi.muni.pa165.dogbarber.exception;

/**
 *
 * @author Severin Simko
 */
public class DogBarberException extends RuntimeException{
    
    private static final long serialVersionUID = 1210696011208789269L;

	public DogBarberException() {
    }

    public DogBarberException(String message) {
        super(message);
    }

    public DogBarberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DogBarberException(Throwable cause) {
        super(cause);
    }

    public DogBarberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
