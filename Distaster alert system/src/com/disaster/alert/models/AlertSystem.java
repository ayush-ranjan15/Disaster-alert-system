package com.disaster.alert.models;

import com.disaster.alert.exceptions.AlertSystemException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Alert system for managing and sending notifications
 * Includes file I/O operations
 */
public class AlertSystem implements AlertNotification {
    private List<Alert> alertHistory;
    private List<String> authorities;
    private String logFilePath;

    // Nested class for alert details
    public class Alert {
        private String severity;
        private String message;
        private LocalDateTime timestamp;
        private String zone;

        public Alert(String severity, String message, String zone) {
            this.severity = severity;
            this.message = message;
            this.timestamp = LocalDateTime.now();
            this.zone = zone;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return String.format("[%s] SEVERITY: %s | ZONE: %s | MESSAGE: %s | TIME: %s",
                    severity.toUpperCase(), severity, zone, message, timestamp.format(formatter));
        }
    }

    // Overloaded Constructors
    public AlertSystem() {
        this.alertHistory = new ArrayList<>();
        this.authorities = new ArrayList<>();
        this.logFilePath = "alert_log.txt";
    }

    public AlertSystem(String logFilePath) {
        this.alertHistory = new ArrayList<>();
        this.authorities = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public AlertSystem(String logFilePath, String... initialAuthorities) {
        this.alertHistory = new ArrayList<>();
        this.authorities = new ArrayList<>(Arrays.asList(initialAuthorities));
        this.logFilePath = logFilePath;
    }

    // Overloaded sendAlert methods
    @Override
    public void sendAlert(String severity, String message) {
        sendAlert(severity, message, "GENERAL");
    }

    public void sendAlert(String severity, String message, String zone) {
        Alert alert = new Alert(severity, message, zone);
        alertHistory.add(alert);
        System.out.println("ALERT SENT: " + alert);
        notifyAuthorities(alert);
    }

    public void sendAlert(String severity, String message, String zone, String contactPersonnel) {
        Alert alert = new Alert(severity, message, zone);
        alertHistory.add(alert);
        System.out.println("ALERT SENT: " + alert);
        System.out.println("Contact: " + contactPersonnel);
        notifyAuthorities(alert);
    }

    // Varargs for multiple recipients
    public void sendAlertToMultiple(String severity, String message, String zone, String... recipients) {
        Alert alert = new Alert(severity, message, zone);
        alertHistory.add(alert);
        System.out.println("ALERT SENT TO " + recipients.length + " RECIPIENTS: " + alert);
        for (String recipient : recipients) {
            System.out.println("  -> " + recipient);
        }
    }

    private void notifyAuthorities(Alert alert) {
        for (String authority : authorities) {
            System.out.println("  Notifying: " + authority);
        }
    }

    public void addAuthority(String authority) {
        if (!authorities.contains(authority)) {
            authorities.add(authority);
        }
    }

    @Override
    public void maintainAlertHistory() {
        try {
            saveAlertHistoryToFile();
        } catch (AlertSystemException e) {
            System.err.println("Error maintaining alert history: " + e.getMessage());
        }
    }

    private void saveAlertHistoryToFile() throws AlertSystemException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Alert alert : alertHistory) {
                writer.write(alert.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AlertSystemException("Failed to save alert history to file: " + logFilePath, e);
        }
    }

    public void loadAlertHistoryFromFile() throws AlertSystemException {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            System.out.println("\n=== Alert History ===");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new AlertSystemException("Failed to load alert history from file: " + logFilePath, e);
        }
    }

    public int getAlertCount() {
        return alertHistory.size();
    }

    public List<Alert> getAlertHistory() {
        return new ArrayList<>(alertHistory);
    }

    public List<String> getAuthorities() {
        return new ArrayList<>(authorities);
    }
}
