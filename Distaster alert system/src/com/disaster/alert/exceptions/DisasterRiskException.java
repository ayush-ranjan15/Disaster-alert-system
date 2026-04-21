package com.disaster.alert.exceptions;

/**
 * Custom exception for disaster risk detection errors
 */
public class DisasterRiskException extends Exception {
    public DisasterRiskException(String message) {
        super(message);
    }

    public DisasterRiskException(String message, Throwable cause) {
        super(message, cause);
    }
}
