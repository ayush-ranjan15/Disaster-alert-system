package com.disaster.alert.models;

import java.util.*;

/**
 * Prediction and Trend Analysis for disaster forecasting
 */
public class PredictionAnalysis {
    private List<Double> historicalData;
    private String parameter;
    private Integer trendWindow;

    // Wrapper class for prediction results
    public static class PredictionResult {
        private Double predictedValue;
        private String trend;
        private Double confidence;

        public PredictionResult(Double predictedValue, String trend, Double confidence) {
            this.predictedValue = predictedValue;
            this.trend = trend;
            this.confidence = confidence;
        }

        @Override
        public String toString() {
            return String.format("Predicted: %.2f | Trend: %s | Confidence: %.1f%%", 
                    predictedValue, trend, confidence * 100);
        }

        public Double getPredictedValue() {
            return predictedValue;
        }

        public String getTrend() {
            return trend;
        }

        public Double getConfidence() {
            return confidence;
        }
    }

    // Overloaded Constructors
    public PredictionAnalysis(String parameter) {
        this.parameter = parameter;
        this.historicalData = new ArrayList<>();
        this.trendWindow = 5;
    }

    public PredictionAnalysis(String parameter, Integer trendWindow) {
        this.parameter = parameter;
        this.historicalData = new ArrayList<>();
        this.trendWindow = trendWindow;
    }

    // Overloaded methods for predictions
    public void addHistoricalData(Double value) {
        historicalData.add(value);
    }

    public void addHistoricalData(Double... values) {
        for (Double value : values) {
            historicalData.add(value);
        }
    }

    public void addHistoricalData(List<Double> values) {
        historicalData.addAll(values);
    }

    // Overloaded prediction methods
    public PredictionResult predictNextValue() {
        if (historicalData.size() < 2) {
            return new PredictionResult(0.0, "INSUFFICIENT_DATA", 0.0);
        }

        Double average = historicalData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        Double lastValue = historicalData.get(historicalData.size() - 1);
        String trend = lastValue > average ? "INCREASING" : "DECREASING";
        Double confidence = Math.min(1.0, historicalData.size() / 10.0);

        return new PredictionResult(lastValue, trend, confidence);
    }

    public PredictionResult predictNextValue(int steps) {
        if (historicalData.size() < steps) {
            return new PredictionResult(0.0, "INSUFFICIENT_DATA", 0.0);
        }

        List<Double> recentData = historicalData.subList(
                Math.max(0, historicalData.size() - steps), 
                historicalData.size());
        
        Double average = recentData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        Double trend = calculateTrend(recentData);
        Double predicted = average + trend;
        Double confidence = Math.min(1.0, recentData.size() / 10.0);

        String trendStr = trend > 0 ? "INCREASING" : "DECREASING";
        return new PredictionResult(predicted, trendStr, confidence);
    }

    public PredictionResult predictWithConfidence(Integer threshold) {
        PredictionResult result = predictNextValue();
        if (result.getConfidence() < threshold / 100.0) {
            return new PredictionResult(0.0, "LOW_CONFIDENCE", result.getConfidence());
        }
        return result;
    }

    private Double calculateTrend(List<Double> data) {
        if (data.size() < 2) return 0.0;
        
        Double sum = 0.0;
        for (int i = 1; i < data.size(); i++) {
            sum += data.get(i) - data.get(i - 1);
        }
        return sum / (data.size() - 1);
    }

    public Double getAverage() {
        return historicalData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public Double getMaximum() {
        return historicalData.stream().mapToDouble(Double::doubleValue).max().orElse(0);
    }

    public Double getMinimum() {
        return historicalData.stream().mapToDouble(Double::doubleValue).min().orElse(0);
    }

    public void displayStatistics() {
        System.out.printf("\n=== Statistics for %s ===\n", parameter);
        System.out.printf("Count: %d\n", historicalData.size());
        System.out.printf("Average: %.2f\n", getAverage());
        System.out.printf("Maximum: %.2f\n", getMaximum());
        System.out.printf("Minimum: %.2f\n", getMinimum());
    }

    public String getParameter() {
        return parameter;
    }

    public List<Double> getHistoricalData() {
        return new ArrayList<>(historicalData);
    }
}
