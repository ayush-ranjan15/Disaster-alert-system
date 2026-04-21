package com.disaster.alert.models;

/**
 * Interface for alert notifications
 */
public interface AlertNotification {
    void sendAlert(String severity, String message);
    void maintainAlertHistory();
}
