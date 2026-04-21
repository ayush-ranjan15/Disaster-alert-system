import com.disaster.alert.models.*;
import com.disaster.alert.services.*;
import com.disaster.alert.exceptions.*;
import java.util.*;
import java.io.IOException;

/**
 * Smart Disaster Alert System - Main Application
 * 
 * Project Component (ECOM F213) Object Oriented Programming
 * Instructor: Anita Agrawal
 * 
 * This system demonstrates:
 * 1. Multiple classes with inheritance and hierarchical design
 * 2. Abstract classes and interfaces
 * 3. Nested classes (static and non-static)
 * 4. Exception handling with custom exceptions
 * 5. File I/O operations
 * 6. Overloaded methods and constructors
 * 7. Varargs usage
 * 8. Wrapper classes
 * 9. Package organization
 * 10. Collections and data structures
 */
public class App {
    
    public static void main(String[] args) {
        try {
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║    SMART DISASTER ALERT SYSTEM - MAIN APPLICATION      ║");
            System.out.println("╚════════════════════════════════════════════════════════╝\n");

            // =========== INITIALIZATION ===========
            initializeSystem();
            
            // =========== MONITORING DEMONSTRATION ===========
            demonstrateEnvironmentalMonitoring();
            
            // =========== DISASTER DETECTION ===========
            demonstrateDisasterDetection();
            
            // =========== PREDICTION & ANALYSIS ===========
            demonstratePredictionAnalysis();
            
            // =========== ALERT SYSTEM ===========
            demonstrateAlertSystem();
            
            // =========== REPORTING ===========
            demonstrateReporting();
            
            // =========== EMERGENCY RESPONSE ===========
            demonstrateEmergencyResponse();
            
            // =========== ADMIN FUNCTIONS ===========
            demonstrateAdminFunctions();

        } catch (Exception e) {
            System.err.println("Critical Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initialize system components
     */
    private static void initializeSystem() {
        System.out.println(">>> INITIALIZING SYSTEM COMPONENTS <<<\n");
        
        // Create admin panel (demonstrates wrapper class usage)
        AdminPanel adminPanel = new AdminPanel();
        System.out.println("✓ Admin Panel initialized");
        System.out.println("✓ Alert System initialized");
        System.out.println("✓ Report Generator initialized\n");
    }

    /**
     * Demonstrate environmental monitoring (Interface implementation)
     */
    private static void demonstrateEnvironmentalMonitoring() throws InvalidReadingException {
        System.out.println("\n>>> ENVIRONMENTAL DATA MONITORING <<<\n");
        
        // Create monitoring stations with overloaded constructors
        MonitoringStation station1 = new MonitoringStation("North Zone");
        MonitoringStation station2 = new MonitoringStation("South Zone", "STA-SOUTH-001");
        
        MonitoringStation.ThresholdConfiguration customThresholds = new MonitoringStation.ThresholdConfiguration();
        customThresholds.setTemperatureMax(55.0);
        customThresholds.setRainfallMax(600.0);
        MonitoringStation station3 = new MonitoringStation("East Zone", "STA-EAST-001", customThresholds);
        
        System.out.println("✓ Created 3 monitoring stations with overloaded constructors\n");

        // Record readings using overloaded recordReading methods
        try {
            System.out.println("--- Recording Environmental Data ---");
            station1.recordReading("temperature", 38.5);
            station1.recordReading("rainfall", 250.0, "mm");
            station1.recordReading("humidity", 75.0);
            station1.recordReading("wind_speed", 45.0);
            station1.displayReadings();

            station2.recordReading("temperature", 32.0);
            station2.recordReading("seismic", 4.5);
            station2.recordReading("rainfall", 100.0, "mm");
            station2.displayReadings();

            station3.recordReading("temperature", 28.0);
            station3.recordReading("humidity", 65.0);
            station3.displayReadings();

            // Analyze data
            try {
                station1.analyzeData();
            } catch (Exception e) {
                System.out.println("Analysis error: " + e.getMessage());
            }
            System.out.println("✓ Environmental data recorded and analyzed\n");

        } catch (InvalidReadingException e) {
            System.out.println("Error recording reading: " + e.getMessage());
        }
    }

    /**
     * Demonstrate disaster detection and risk analysis
     */
    private static void demonstrateDisasterDetection() throws DisasterRiskException {
        System.out.println("\n>>> DISASTER RISK DETECTION <<<\n");
        
        DisasterDetection detector = new DisasterDetection("North Zone");
        
        // Add environmental readings
        detector.addReading("temperature", 48.0);
        detector.addReading("rainfall", 350.0);
        detector.addReading("seismic", 6.5);
        detector.addReading("wind_speed", 85.0);
        detector.addReading("humidity", 88.0);

        try {
            // Overloaded analyze methods
            detector.analyzeData();
            
            System.out.println("\n--- Analyzing specific disaster types ---");
            DisasterDetection detector2 = new DisasterDetection("South Zone");
            detector2.addReading("temperature", 50.0);
            detector2.analyzeData("heatwave");
            
            DisasterDetection detector3 = new DisasterDetection("East Zone");
            detector3.addReading("rainfall", 400.0);
            detector3.addReading("humidity", 90.0);
            detector3.analyzeData("flood");
            
            // Varargs usage in analyzeMultipleParameters
            System.out.println("\n--- Analyzing multiple parameters with varargs ---");
            detector.analyzeMultipleParameters("temperature", "rainfall", "seismic");
            
            System.out.println("✓ Disaster detection completed\n");

        } catch (DisasterRiskException e) {
            System.out.println("Disaster Risk Error: " + e.getMessage());
        }
    }

    /**
     * Demonstrate prediction and trend analysis
     */
    private static void demonstratePredictionAnalysis() {
        System.out.println("\n>>> PREDICTION AND TREND ANALYSIS <<<\n");
        
        // Create prediction analysis with overloaded constructors
        PredictionAnalysis tempAnalysis = new PredictionAnalysis("Temperature");
        PredictionAnalysis rainfallAnalysis = new PredictionAnalysis("Rainfall", 7);
        
        // Add historical data using overloaded methods
        System.out.println("--- Temperature Trend ---");
        tempAnalysis.addHistoricalData(25.0, 26.5, 28.0, 30.5, 32.0, 35.0, 38.5);
        
        // Overloaded prediction methods
        PredictionAnalysis.PredictionResult result1 = tempAnalysis.predictNextValue();
        System.out.println("Next prediction: " + result1);
        
        PredictionAnalysis.PredictionResult result2 = tempAnalysis.predictNextValue(5);
        System.out.println("5-step prediction: " + result2);
        
        tempAnalysis.displayStatistics();

        System.out.println("\n--- Rainfall Trend ---");
        List<Double> rainfallData = Arrays.asList(50.0, 75.0, 100.0, 150.0, 200.0);
        rainfallAnalysis.addHistoricalData(rainfallData);
        
        PredictionAnalysis.PredictionResult result3 = rainfallAnalysis.predictNextValue();
        System.out.println("Next prediction: " + result3);
        
        rainfallAnalysis.displayStatistics();
        System.out.println("✓ Prediction analysis completed\n");
    }

    /**
     * Demonstrate alert and notification system
     */
    private static void demonstrateAlertSystem() {
        System.out.println("\n>>> ALERT AND NOTIFICATION SYSTEM <<<\n");
        
        // Create alert system with overloaded constructors
        AlertSystem alertSystem = new AlertSystem("alert_log.txt");
        
        // Add authorities
        alertSystem.addAuthority("Fire Department");
        alertSystem.addAuthority("Police Department");
        alertSystem.addAuthority("Medical Services");
        
        // Overloaded sendAlert methods
        System.out.println("--- Sending Alerts ---");
        alertSystem.sendAlert("HIGH", "Excessive rainfall detected");
        
        alertSystem.sendAlert("CRITICAL", "Seismic activity detected", "East Zone");
        
        alertSystem.sendAlert("MODERATE", "High temperature", "North Zone", "Civil Administrator");
        
        // Varargs usage in sendAlertToMultiple
        alertSystem.sendAlertToMultiple("HIGH", "Possible flood risk", "South Zone", 
                                        "Evacuation Officer", "District Magistrate", "Police Chief");
        
        // Maintain alert history (File I/O)
        alertSystem.maintainAlertHistory();
        System.out.println("✓ Alerts sent and logged\n");
    }

    /**
     * Demonstrate reporting and visualization
     */
    private static void demonstrateReporting() {
        System.out.println("\n>>> REPORTING AND VISUALIZATION <<<\n");
        
        ReportGenerator reportGen = new ReportGenerator("reports.txt");
        
        // Environmental report
        Map<String, Double> readings = new HashMap<>();
        readings.put("Temperature", 38.5);
        readings.put("Rainfall", 250.0);
        readings.put("Humidity", 75.0);
        reportGen.generateEnvironmentalReport("North Zone", readings);
        
        // Disaster report
        List<String> risks = Arrays.asList("Flood Risk", "Heatwave Warning");
        reportGen.generateDisasterReport("South Zone", risks);
        
        // Varargs in disaster report
        reportGen.generateDisasterReport("East Zone", 
                "Seismic activity detected", 
                "Building structural risk", 
                "Evacuation recommended");
        
        // Alert report
        reportGen.generateAlertReport("North Zone", 5, "HIGH");
        
        // Save reports to file (File I/O)
        try {
            reportGen.saveAllReportsToFile();
            reportGen.displayAllReports();
        } catch (IOException e) {
            System.out.println("Error saving reports: " + e.getMessage());
        }
        
        System.out.println("✓ Reports generated and saved\n");
    }

    /**
     * Demonstrate emergency response support
     */
    private static void demonstrateEmergencyResponse() {
        System.out.println("\n>>> EMERGENCY RESPONSE SUPPORT <<<\n");
        
        EmergencyResponseManager emergencyManager = new EmergencyResponseManager();
        
        // Add emergency contacts using overloaded methods
        emergencyManager.addContact("Fire Department", "911");
        emergencyManager.addContact("Police Department", "100");
        emergencyManager.addContact("Hospital", "ambulance@hospital.com", "9876543210", "Medical Emergency: Yes");
        
        // Varargs usage in addContact
        emergencyManager.addContact("Disaster Management Authority", 
                                   "dmauth@gov.in", 
                                   "1077", 
                                   "24/7 Hotline",
                                   "www.dmauth.gov.in");
        
        emergencyManager.displayEmergencyContacts();
        
        // Display safety guidelines
        System.out.println("\n--- Safety Guidelines ---");
        emergencyManager.suggestPrecautionarMeasures("FLOOD");
        emergencyManager.suggestPrecautionarMeasures("EARTHQUAKE");
        emergencyManager.suggestPrecautionarMeasures("HEATWAVE");
        
        // Track response actions using varargs
        emergencyManager.trackResponseActions("Evacuation Initiated", 
                                             "Area: North Zone", 
                                             "People: 5000", 
                                             "Buses: 50",
                                             "Shelters: 3");
        
        System.out.println("✓ Emergency response configured\n");
    }

    /**
     * Demonstrate admin panel functions
     */
    private static void demonstrateAdminFunctions() {
        System.out.println("\n>>> ADMIN PANEL AND SYSTEM MANAGEMENT <<<\n");
        
        AdminPanel adminPanel = new AdminPanel();
        
        // Register stations using overloaded methods
        MonitoringStation station1 = new MonitoringStation("North Zone", "ADMIN-STA-001");
        MonitoringStation station2 = new MonitoringStation("South Zone", "ADMIN-STA-002");
        MonitoringStation station3 = new MonitoringStation("East Zone", "ADMIN-STA-003");
        MonitoringStation station4 = new MonitoringStation("West Zone", "ADMIN-STA-004");
        
        // Register single station
        adminPanel.registerStation(station1);
        
        // Register multiple stations using varargs
        adminPanel.registerStations(station2, station3, station4);
        
        // Get and display system statistics (Wrapper class usage)
        AdminPanel.SystemStatistics stats = adminPanel.getSystemStatistics();
        System.out.println("\nSystem Statistics: " + stats);
        
        // Display comprehensive report
        adminPanel.displaySystemStatus();
        adminPanel.generateSystemReport();
        
        System.out.println("✓ Admin operations completed\n");
    }
}
