package com.disaster.alert.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Interface for environmental monitoring
 */
public interface EnvironmentalMonitor {
    void recordReading(String parameter, double value) throws Exception;
    void displayReadings();
    LocalDateTime getLastUpdateTime();
}
