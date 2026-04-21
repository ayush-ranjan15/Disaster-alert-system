package com.disaster.alert.services;

import com.disaster.alert.models.*;
import java.util.*;

/**
 * Administrator control and system management
 */
public class AdminPanel {
    private List<MonitoringStation> stations;
    private AlertSystem alertSystem;
    private ReportGenerator reportGenerator;

    // Wrapper class for system statistics
    public static class SystemStatistics {
        private Integer activeStations;
        private Integer totalAlerts;
        private Integer generatedReports;
        private Long systemUptime;

        public SystemStatistics(Integer activeStations, Integer totalAlerts, 
                               Integer generatedReports, Long systemUptime) {
            this.activeStations = activeStations;
            this.totalAlerts = totalAlerts;
            this.generatedReports = generatedReports;
            this.systemUptime = systemUptime;
        }

        @Override
        public String toString() {
            return String.format("Active Stations: %d | Total Alerts: %d | Reports: %d | Uptime: %d ms",
                    activeStations, totalAlerts, generatedReports, systemUptime);
        }
    }

    // Overloaded Constructors
    public AdminPanel() {
        this.stations = new ArrayList<>();
        this.alertSystem = new AlertSystem();
        this.reportGenerator = new ReportGenerator();
    }

    public AdminPanel(AlertSystem alertSystem, ReportGenerator reportGenerator) {
        this.stations = new ArrayList<>();
        this.alertSystem = alertSystem;
        this.reportGenerator = reportGenerator;
    }

    // Overloaded methods for admin functions
    public void registerStation(MonitoringStation station) {
        if (!stationExists(station.getStationId())) {
            stations.add(station);
            System.out.println("Station registered: " + station.getStationId());
        } else {
            System.out.println("Station already exists: " + station.getStationId());
        }
    }

    public void registerStations(MonitoringStation... stationArray) {
        for (MonitoringStation station : stationArray) {
            registerStation(station);
        }
    }

    public void deregisterStation(String stationId) {
        stations.removeIf(s -> s.getStationId().equals(stationId));
        System.out.println("Station deregistered: " + stationId);
    }

    public void displaySystemStatus() {
        System.out.println("\n=== System Status ===");
        System.out.println("Active Monitoring Stations: " + stations.size());
        System.out.println("Total Alerts Generated: " + alertSystem.getAlertCount());
        System.out.println("Total Reports Generated: " + reportGenerator.getTotalReports());
    }

    public SystemStatistics getSystemStatistics() {
        return new SystemStatistics(
                stations.size(),
                alertSystem.getAlertCount(),
                reportGenerator.getTotalReports(),
                System.currentTimeMillis()
        );
    }

    public void generateSystemReport() {
        System.out.println("\n=== Comprehensive System Report ===");
        System.out.println(getSystemStatistics());
        displayAllStations();
    }

    private void displayAllStations() {
        System.out.println("\n--- Registered Stations ---");
        for (MonitoringStation station : stations) {
            System.out.println("  " + station.getStationId() + " (" + station.getZone() + ")");
        }
    }

    private boolean stationExists(String stationId) {
        return stations.stream().anyMatch(s -> s.getStationId().equals(stationId));
    }

    public MonitoringStation getStation(String stationId) {
        return stations.stream().filter(s -> s.getStationId().equals(stationId)).findFirst().orElse(null);
    }

    public List<MonitoringStation> getAllStations() {
        return new ArrayList<>(stations);
    }

    public AlertSystem getAlertSystem() {
        return alertSystem;
    }

    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }
}
