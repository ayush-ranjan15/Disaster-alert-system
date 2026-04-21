package com.disaster.alert.exceptions;

/**
 * Custom exception for file operations and I/O errors
 */
public class AlertSystemException extends Exception {
    public AlertSystemException(String message) {
        super(message);
    }

    public AlertSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
