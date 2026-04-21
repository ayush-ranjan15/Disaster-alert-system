# Smart Disaster Alert System

## Project Overview

A comprehensive **Object-Oriented Java application** for monitoring environmental conditions, detecting disaster risks, predicting trends, generating alerts, and coordinating emergency responses. This system is designed to provide real-time disaster management capabilities.

## Project Component Requirements (ECOM F213)

This project satisfies all minimum requirements:

### ✅ Core Requirements Met

| Requirement | Implementation | Count |
|-------------|-----------------|-------|
| **Nested Classes** | Static (ThresholdConfiguration), Non-static (Alert, SafetyGuideline) | 3+ |
| **Abstract Classes** | EnvironmentalData (base class with abstract methods) | 1 |
| **Interfaces** | EnvironmentalMonitor, AlertNotification | 2 |
| **Hierarchical Inheritance** | MonitoringStation extends EnvironmentalData; DisasterDetection extends EnvironmentalData | 2 |
| **Classes** | MonitoringStation, DisasterDetection, AlertSystem, ReportGenerator, PredictionAnalysis, EmergencyResponseManager, AdminPanel, App | 8 |
| **Packages** | com.disaster.alert.models, com.disaster.alert.services, com.disaster.alert.exceptions | 3 |
| **Exception Handling** | InvalidReadingException, DisasterRiskException, AlertSystemException | 3 |
| **File I/O** | BufferedWriter, FileWriter, BufferedReader, FileReader (in AlertSystem, ReportGenerator) | 4 |
| **Overloaded Methods** | 10+ overloaded methods across classes | 10+ |
| **Overloaded Constructors** | 2+ in MonitoringStation, AlertSystem, AdminPanel, PredictionAnalysis | 8+ |
| **Varargs** | recordReadings(...), sendAlertToMultiple(...), registerStations(...), analyzeMultipleParameters(...) | 5+ |
| **Wrapper Classes** | PredictionResult, SystemStatistics | 2 |

## Folder Structure

```
d:\New folder (3)\Distaster alert system\
├── src/
│   ├── App.java (Main application)
│   └── com/
│       └── disaster/
│           └── alert/
│               ├── models/
│               │   ├── EnvironmentalMonitor.java
│               │   ├── AlertNotification.java
│               │   ├── EnvironmentalData.java
│               │   ├── MonitoringStation.java
│               │   ├── DisasterDetection.java
│               │   ├── PredictionAnalysis.java
│               │   ├── AlertSystem.java
│               │   └── ReportGenerator.java
│               ├── services/
│               │   ├── EmergencyResponseManager.java
│               │   └── AdminPanel.java
│               └── exceptions/
│                   ├── InvalidReadingException.java
│                   ├── DisasterRiskException.java
│                   └── AlertSystemException.java
├── bin/ (compiled classes)
├── lib/ (dependencies)
├── README.md
└── alert_log.txt, reports.txt (generated at runtime)
```

## System Features

### 1. **Environmental Data Monitoring**
- Record multiple environmental parameters (temperature, rainfall, humidity, wind speed, seismic data)
- Validate readings against threshold values
- Support for multiple monitoring stations across different zones
- Real-time data display

### 2. **Disaster Risk Detection**
- Detect flood risks based on rainfall levels
- Identify earthquake risks through seismic readings
- Monitor heatwave conditions
- Track storm conditions
- Support for specific disaster type analysis

### 3. **Prediction and Trend Analysis**
- Historical data tracking for environmental parameters
- Trend prediction with confidence levels
- Statistical analysis (average, maximum, minimum)
- Multi-step ahead predictions

### 4. **Alert and Notification System**
- Generate alerts with severity levels (LOW, MODERATE, HIGH, CRITICAL)
- Send notifications to multiple authorities
- Maintain alert history with timestamps
- File-based alert logging

### 5. **Reporting and Visualization**
- Generate environmental data reports
- Create disaster risk assessment reports
- Produce alert summaries
- Save reports to files for archival

### 6. **Emergency Response Support**
- Provide safety guidelines for different disaster types
- Maintain emergency contact information
- Track emergency response actions
- Store shelter locations

### 7. **Admin Management**
- Register/deregister monitoring stations
- Monitor system status and statistics
- Generate comprehensive system reports
- View active stations and metrics

## Key OOP Concepts Demonstrated

### **Abstraction**
- `EnvironmentalData` abstract class defines contract for all data types
- Abstract methods like `analyzeData()` and `isAbnormal()`

### **Inheritance**
- `MonitoringStation` and `DisasterDetection` extend `EnvironmentalData`
- Hierarchical design allows code reuse and specialization

### **Polymorphism**
- **Method Overloading**: Multiple versions of methods like `recordReading()`, `sendAlert()`, `analyzeData()`
- **Method Overriding**: Child classes override abstract methods with specific implementations
- **Varargs**: Flexible parameter passing using variable-length argument lists

### **Encapsulation**
- Private attributes with public getter/setter methods
- Protected access in abstract classes for derived classes
- Nested classes for logical grouping

### **Interfaces**
- `EnvironmentalMonitor` - Define monitoring contract
- `AlertNotification` - Define alert management contract

### **Nested Classes**
- Static nested: `ThresholdConfiguration`, `DisasterType`, `PredictionResult`, `SystemStatistics`
- Non-static nested: `Alert`, `SafetyGuideline`, `ReportEntry`

### **Exception Handling**
- Custom exceptions with meaningful error messages
- Try-catch blocks for robust error handling
- Exception propagation and handling

### **Collections**
- ArrayList, HashMap, List for data management
- Iterator and Stream API for data processing

### **Wrapper Classes**
- `PredictionResult` - Wraps prediction data
- `SystemStatistics` - Wraps system metrics

### **File I/O**
- Reading/Writing files with BufferedReader and BufferedWriter
- Alert history persistence
- Report archival

## Compilation Instructions

### Windows (PowerShell/CMD)
```bash
cd d:\New folder (3)\Distaster alert system

# Compile all Java files
javac -d bin src\*.java src\com\disaster\alert\models\*.java src\com\disaster\alert\services\*.java src\com\disaster\alert\exceptions\*.java

# Run the application
java -cp bin App
```

### Linux/Mac
```bash
cd "d:/New folder (3)/Distaster alert system"

# Compile
javac -d bin src/*.java src/com/disaster/alert/models/*.java src/com/disaster/alert/services/*.java src/com/disaster/alert/exceptions/*.java

# Run
java -cp bin App
```

## Usage Example

```java
// Create monitoring station
MonitoringStation station = new MonitoringStation("North Zone");

// Record environmental data
station.recordReading("temperature", 38.5);
station.recordReading("rainfall", 250.0, "mm");

// Analyze data
station.analyzeData();

// Create disaster detector
DisasterDetection detector = new DisasterDetection("North Zone");
detector.addReading("temperature", 48.0);
detector.analyzeData();

// Generate alerts
AlertSystem alertSystem = new AlertSystem("alert_log.txt");
alertSystem.sendAlert("HIGH", "Excessive rainfall detected", "North Zone");

// Generate reports
ReportGenerator reports = new ReportGenerator("reports.txt");
reports.generateEnvironmentalReport("North Zone", readings);
```

## Output Features

- **Real-time console output** with formatted alerts and data displays
- **File logging** for alerts and reports
- **System statistics** showing active stations, alert counts, and reports
- **Detailed exception messages** for error handling
- **Historical data tracking** for trend analysis

## Compliance with Course Requirements

✓ Minimum 4 classes (8 created)  
✓ Nested classes at least 1 (3+ types created)  
✓ Abstract class minimum 1 (1 created)  
✓ Interface minimum 1 (2 created)  
✓ Hierarchical inheritance at least 1 (2+ created)  
✓ Packages implemented  
✓ Exception handling at least 3 cases (3+ custom exceptions)  
✓ File I/O operations implemented  
✓ Overloaded methods minimum 3 (10+ implemented)  
✓ Overloaded constructors minimum 2 (8+ implemented)  
✓ Varargs overloading minimum 1 (5+ implemented)  
✓ Wrappers used (PredictionResult, SystemStatistics)

---

**Developed for**: ECOM F213 - Object Oriented Programming  
**Instructor**: Anita Agrawal  
**Academic Year**: 2025-2026

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
