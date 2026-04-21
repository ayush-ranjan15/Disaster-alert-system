package com.disaster.alert.models;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Abstract class for environmental data
 */
public abstract class EnvironmentalData {
    protected String zone;
    protected LocalDateTime timestamp;
    protected Map<String, Double> readings;

    public EnvironmentalData(String zone) {
        this.zone = zone;
        this.timestamp = LocalDateTime.now();
        this.readings = new HashMap<>();
    }

    public abstract void analyzeData() throws Exception;
    public abstract boolean isAbnormal();

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addReading(String parameter, Double value) {
        readings.put(parameter, value);
    }

    public Double getReading(String parameter) {
        return readings.get(parameter);
    }

    public Map<String, Double> getAllReadings() {
        return new HashMap<>(readings);
    }
}
