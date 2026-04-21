package com.disaster.alert.models;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Reporting and Visualization system for disaster management
 */
public class ReportGenerator {
    private List<ReportEntry> reports;
    private String reportFilePath;

    // Nested class for report entries
    public class ReportEntry {
        private String title;
        private String content;
        private LocalDateTime createdTime;
        private String reportType;

        public ReportEntry(String title, String content, String reportType) {
            this.title = title;
            this.content = content;
            this.reportType = reportType;
            this.createdTime = LocalDateTime.now();
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return String.format("\n[%s] %s\nType: %s\nCreated: %s\n%s\n",
                    reportType.toUpperCase(), title, reportType, 
                    createdTime.format(formatter), content);
        }
    }

    // Overloaded Constructors
    public ReportGenerator() {
        this.reports = new ArrayList<>();
        this.reportFilePath = "reports.txt";
    }

    public ReportGenerator(String reportFilePath) {
        this.reports = new ArrayList<>();
        this.reportFilePath = reportFilePath;
    }

    // Overloaded methods for report generation
    public void generateEnvironmentalReport(String zone, Map<String, Double> readings) {
        StringBuilder content = new StringBuilder();
        content.append("Zone: ").append(zone).append("\n");
        content.append("Readings:\n");
        for (Map.Entry<String, Double> entry : readings.entrySet()) {
            content.append(String.format("  %s: %.2f\n", entry.getKey(), entry.getValue()));
        }
        ReportEntry report = new ReportEntry("Environmental Data Report - " + zone, 
                content.toString(), "ENVIRONMENTAL");
        reports.add(report);
        System.out.println("Generated: " + report.title);
    }

    public void generateDisasterReport(String zone, List<String> risks) {
        StringBuilder content = new StringBuilder();
        content.append("Zone: ").append(zone).append("\n");
        content.append("Detected Risks:\n");
        for (String risk : risks) {
            content.append("  - ").append(risk).append("\n");
        }
        ReportEntry report = new ReportEntry("Disaster Risk Assessment - " + zone, 
                content.toString(), "DISASTER");
        reports.add(report);
        System.out.println("Generated: " + report.title);
    }

    public void generateDisasterReport(String zone, String... riskDetails) {
        StringBuilder content = new StringBuilder();
        content.append("Zone: ").append(zone).append("\n");
        content.append("Risk Details:\n");
        for (String detail : riskDetails) {
            content.append("  - ").append(detail).append("\n");
        }
        ReportEntry report = new ReportEntry("Detailed Risk Report - " + zone, 
                content.toString(), "DISASTER");
        reports.add(report);
    }

    public void generateAlertReport(String zone, Integer alertCount, String severity) {
        String content = String.format("Zone: %s\nTotal Alerts: %d\nMax Severity: %s", 
                zone, alertCount, severity);
        ReportEntry report = new ReportEntry("Alert Summary - " + zone, 
                content, "ALERT");
        reports.add(report);
        System.out.println("Generated: " + report.title);
    }

    public void saveReportToFile(ReportEntry report) throws IOException {
        try (FileWriter writer = new FileWriter(reportFilePath, true)) {
            writer.write(report.toString());
            writer.write("\n" + "=".repeat(50) + "\n");
        }
    }

    public void saveAllReportsToFile() throws IOException {
        try (FileWriter writer = new FileWriter(reportFilePath, false)) {
            for (ReportEntry report : reports) {
                writer.write(report.toString());
                writer.write("\n" + "=".repeat(50) + "\n");
            }
        }
        System.out.println("All reports saved to: " + reportFilePath);
    }

    public void displayAllReports() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("GENERATED REPORTS");
        System.out.println("=".repeat(50));
        for (ReportEntry report : reports) {
            System.out.println(report);
        }
    }

    public Integer getTotalReports() {
        return reports.size();
    }

    public List<ReportEntry> getReports() {
        return new ArrayList<>(reports);
    }
}
