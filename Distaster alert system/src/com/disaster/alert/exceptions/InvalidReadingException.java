package com.disaster.alert.exceptions;

/**
 * Custom exception for invalid environmental readings
 */
public class InvalidReadingException extends Exception {
    public InvalidReadingException(String message) {
        super(message);
    }

    public InvalidReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
