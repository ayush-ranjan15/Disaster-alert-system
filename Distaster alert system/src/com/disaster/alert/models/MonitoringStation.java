package com.disaster.alert.models;

import com.disaster.alert.exceptions.InvalidReadingException;
import java.util.*;

/**
 * Concrete class implementing EnvironmentalData for monitoring
 * Includes nested class for thresholds
 */
public class MonitoringStation extends EnvironmentalData implements EnvironmentalMonitor {
    private String stationId;
    private ThresholdConfiguration thresholds;
    private List<String> historyLog;

    // Nested static class for threshold management
    public static class ThresholdConfiguration {
        private double temperatureMax;
        private double temperatureMin;
        private double rainfallMax;
        private double humidityMax;
        private double seismicMax;

        public ThresholdConfiguration() {
            this.temperatureMax = 50.0;
            this.temperatureMin = -30.0;
            this.rainfallMax = 500.0;
            this.humidityMax = 95.0;
            this.seismicMax = 7.5;
        }

        public void setTemperatureMax(double value) {
            this.temperatureMax = value;
        }

        public void setRainfallMax(double value) {
            this.rainfallMax = value;
        }

        public boolean isTemperatureValid(double value) {
            return value >= temperatureMin && value <= temperatureMax;
        }

        public boolean isRainfallValid(double value) {
            return value >= 0 && value <= rainfallMax;
        }

        public boolean isSeismicValid(double value) {
            return value >= 0 && value <= seismicMax;
        }
    }

    // Overloaded Constructors
    public MonitoringStation(String zone) {
        super(zone);
        this.stationId = "STA-" + UUID.randomUUID().toString().substring(0, 8);
        this.thresholds = new ThresholdConfiguration();
        this.historyLog = new ArrayList<>();
    }

    public MonitoringStation(String zone, String stationId) {
        super(zone);
        this.stationId = stationId;
        this.thresholds = new ThresholdConfiguration();
        this.historyLog = new ArrayList<>();
    }

    public MonitoringStation(String zone, String stationId, ThresholdConfiguration thresholds) {
        super(zone);
        this.stationId = stationId;
        this.thresholds = thresholds;
        this.historyLog = new ArrayList<>();
    }

    // Overloaded recordReading methods
    @Override
    public void recordReading(String parameter, double value) throws InvalidReadingException {
        validateReading(parameter, value);
        addReading(parameter, value);
        historyLog.add(String.format("[%s] %s: %.2f", timestamp, parameter, value));
    }

    public void recordReading(String parameter, double value, String unit) throws InvalidReadingException {
        validateReading(parameter, value);
        addReading(parameter, value);
        historyLog.add(String.format("[%s] %s: %.2f %s", timestamp, parameter, value, unit));
    }

    public void recordReadings(String... parameters) throws InvalidReadingException {
        if (parameters.length % 2 != 0) {
            throw new InvalidReadingException("Parameters must be in pairs of (name, value)");
        }
        // Note: Varargs implementation for flexibility
        historyLog.add(String.format("[%s] Batch recording with %d parameters", timestamp, parameters.length / 2));
    }

    private void validateReading(String parameter, double value) throws InvalidReadingException {
        switch (parameter.toLowerCase()) {
            case "temperature":
                if (!thresholds.isTemperatureValid(value)) {
                    throw new InvalidReadingException("Temperature " + value + " exceeds thresholds");
                }
                break;
            case "rainfall":
                if (!thresholds.isRainfallValid(value)) {
                    throw new InvalidReadingException("Rainfall " + value + " exceeds maximum");
                }
                break;
            case "seismic":
                if (!thresholds.isSeismicValid(value)) {
                    throw new InvalidReadingException("Seismic reading " + value + " exceeds maximum");
                }
                break;
        }
    }

    @Override
    public void analyzeData() throws Exception {
        if (readings.isEmpty()) {
            throw new Exception("No readings available for analysis");
        }

        for (Map.Entry<String, Double> entry : readings.entrySet()) {
            String param = entry.getKey();
            Double value = entry.getValue();

            if ((param.equalsIgnoreCase("temperature") && !thresholds.isTemperatureValid(value)) ||
                (param.equalsIgnoreCase("rainfall") && !thresholds.isRainfallValid(value)) ||
                (param.equalsIgnoreCase("seismic") && !thresholds.isSeismicValid(value))) {
                historyLog.add(String.format("[ALERT] Abnormal %s: %.2f", param, value));
            }
        }
    }

    @Override
    public boolean isAbnormal() {
        for (Map.Entry<String, Double> entry : readings.entrySet()) {
            String param = entry.getKey();
            Double value = entry.getValue();

            if ((param.equalsIgnoreCase("temperature") && !thresholds.isTemperatureValid(value)) ||
                (param.equalsIgnoreCase("rainfall") && !thresholds.isRainfallValid(value))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayReadings() {
        System.out.println("\n=== Station " + stationId + " (" + zone + ") ===");
        for (Map.Entry<String, Double> entry : readings.entrySet()) {
            System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public java.time.LocalDateTime getLastUpdateTime() {
        return timestamp;
    }

    public String getStationId() {
        return stationId;
    }

    public ThresholdConfiguration getThresholds() {
        return thresholds;
    }

    public List<String> getHistoryLog() {
        return new ArrayList<>(historyLog);
    }
}
