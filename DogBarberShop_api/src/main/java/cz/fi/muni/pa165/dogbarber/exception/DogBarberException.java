/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.exception;

/**
 *
 * @author Severin Simko
 */
public class DogBarberException extends RuntimeException{
    
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
