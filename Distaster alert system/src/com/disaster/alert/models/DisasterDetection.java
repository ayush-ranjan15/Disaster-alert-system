package com.disaster.alert.models;

import com.disaster.alert.exceptions.DisasterRiskException;
import java.util.*;

/**
 * Disaster Risk Detection and Analysis
 */
public class DisasterDetection extends EnvironmentalData {
    private Map<String, Double> thresholdValues;
    private List<String> detectedRisks;

    // Nested enum for disaster types
    public enum DisasterType {
        FLOOD("Flood"), 
        EARTHQUAKE("Earthquake"), 
        HEATWAVE("Heatwave"), 
        STORM("Storm");

        private String displayName;

        DisasterType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Overloaded Constructors
    public DisasterDetection(String zone) {
        super(zone);
        this.detectedRisks = new ArrayList<>();
        this.thresholdValues = initializeThresholds();
    }

    public DisasterDetection(String zone, Map<String, Double> customThresholds) {
        super(zone);
        this.detectedRisks = new ArrayList<>();
        this.thresholdValues = customThresholds;
    }

    // Overloaded methods for risk detection
    @Override
    public void analyzeData() throws DisasterRiskException {
        if (readings.isEmpty()) {
            throw new DisasterRiskException("No environmental data available for risk analysis");
        }

        detectFloodRisk();
        detectEarthquakeRisk();
        detectHeatwaveRisk();
        detectStormRisk();
    }

    public void analyzeData(String specificDisasterType) throws DisasterRiskException {
        if (readings.isEmpty()) {
            throw new DisasterRiskException("No environmental data for analysis");
        }

        switch (specificDisasterType.toLowerCase()) {
            case "flood":
                detectFloodRisk();
                break;
            case "earthquake":
                detectEarthquakeRisk();
                break;
            case "heatwave":
                detectHeatwaveRisk();
                break;
            case "storm":
                detectStormRisk();
                break;
            default:
                throw new DisasterRiskException("Unknown disaster type: " + specificDisasterType);
        }
    }

    // Varargs method for analyzing multiple parameters
    public void analyzeMultipleParameters(String... parameters) throws DisasterRiskException {
        if (parameters.length == 0) {
            throw new DisasterRiskException("At least one parameter required");
        }
        System.out.println("Analyzing parameters: " + Arrays.toString(parameters));
        for (String param : parameters) {
            if (readings.containsKey(param)) {
                System.out.println("  - " + param + ": " + readings.get(param));
            }
        }
    }

    private void detectFloodRisk() {
        Double rainfall = readings.get("rainfall");
        if (rainfall != null && rainfall > thresholdValues.get("rainfall_high")) {
            detectedRisks.add(DisasterType.FLOOD.getDisplayName());
            System.out.println("FLOOD RISK DETECTED in " + zone + ": Rainfall = " + rainfall + "mm");
        }
    }

    private void detectEarthquakeRisk() {
        Double seismic = readings.get("seismic");
        if (seismic != null && seismic > thresholdValues.get("seismic_threshold")) {
            detectedRisks.add(DisasterType.EARTHQUAKE.getDisplayName());
            System.out.println("EARTHQUAKE RISK DETECTED in " + zone + ": Magnitude = " + seismic);
        }
    }

    private void detectHeatwaveRisk() {
        Double temperature = readings.get("temperature");
        if (temperature != null && temperature > thresholdValues.get("temp_high")) {
            detectedRisks.add(DisasterType.HEATWAVE.getDisplayName());
            System.out.println("HEATWAVE RISK DETECTED in " + zone + ": Temperature = " + temperature + "°C");
        }
    }

    private void detectStormRisk() {
        Double windSpeed = readings.get("wind_speed");
        Double humidity = readings.get("humidity");
        if ((windSpeed != null && windSpeed > thresholdValues.get("wind_speed_threshold")) ||
            (humidity != null && humidity > thresholdValues.get("humidity_threshold"))) {
            detectedRisks.add(DisasterType.STORM.getDisplayName());
            System.out.println("STORM RISK DETECTED in " + zone);
        }
    }

    @Override
    public boolean isAbnormal() {
        return !detectedRisks.isEmpty();
    }

    private Map<String, Double> initializeThresholds() {
        Map<String, Double> thresholds = new HashMap<>();
        thresholds.put("temp_high", 45.0);
        thresholds.put("temp_low", -20.0);
        thresholds.put("rainfall_high", 300.0);
        thresholds.put("seismic_threshold", 6.0);
        thresholds.put("humidity_threshold", 85.0);
        thresholds.put("wind_speed_threshold", 80.0);
        return thresholds;
    }

    public List<String> getDetectedRisks() {
        return new ArrayList<>(detectedRisks);
    }

    public void clearDetectedRisks() {
        detectedRisks.clear();
    }
}
