package com.disaster.alert.services;

import com.disaster.alert.models.*;
import java.util.*;

/**
 * Emergency Response Support system
 */
public class EmergencyResponseManager {
    private List<SafetyGuideline> guidelines;
    private Map<String, List<String>> contactInformation;

    // Nested class for safety guidelines
    public class SafetyGuideline {
        private String disasterType;
        private List<String> precautions;
        private String shelterLocation;

        public SafetyGuideline(String disasterType, String shelterLocation) {
            this.disasterType = disasterType;
            this.shelterLocation = shelterLocation;
            this.precautions = new ArrayList<>();
        }

        public void addPrecaution(String precaution) {
            precautions.add(precaution);
        }

        public void displayGuidelines() {
            System.out.println("\n=== " + disasterType.toUpperCase() + " Safety Guidelines ===");
            System.out.println("Shelter Location: " + shelterLocation);
            System.out.println("Precautions:");
            for (String precaution : precautions) {
                System.out.println("  - " + precaution);
            }
        }

        public String getDisasterType() {
            return disasterType;
        }

        public List<String> getPrecautions() {
            return new ArrayList<>(precautions);
        }
    }

    // Overloaded Constructors
    public EmergencyResponseManager() {
        this.guidelines = new ArrayList<>();
        this.contactInformation = new HashMap<>();
        initializeDefaultGuidelines();
    }

    public EmergencyResponseManager(List<SafetyGuideline> customGuidelines) {
        this.guidelines = customGuidelines;
        this.contactInformation = new HashMap<>();
    }

    // Overloaded methods for emergency response
    public void addContact(String authority, String phoneNumber) {
        contactInformation.computeIfAbsent(authority, k -> new ArrayList<>()).add(phoneNumber);
    }

    public void addContact(String authority, String... contactDetails) {
        List<String> contacts = new ArrayList<>(Arrays.asList(contactDetails));
        contactInformation.put(authority, contacts);
    }

    public void displayEmergencyContacts() {
        System.out.println("\n=== Emergency Contact Information ===");
        for (Map.Entry<String, List<String>> entry : contactInformation.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String contact : entry.getValue()) {
                System.out.println("  " + contact);
            }
        }
    }

    public void suggestPrecautionarMeasures(String disasterType) {
        for (SafetyGuideline guideline : guidelines) {
            if (guideline.getDisasterType().equalsIgnoreCase(disasterType)) {
                guideline.displayGuidelines();
                return;
            }
        }
        System.out.println("No guidelines found for: " + disasterType);
    }

    public void trackResponseActions(String action, String... details) {
        System.out.println("\nResponse Action Recorded: " + action);
        for (String detail : details) {
            System.out.println("  - " + detail);
        }
    }

    private void initializeDefaultGuidelines() {
        SafetyGuideline floodGuideline = new SafetyGuideline("FLOOD", "Higher Ground Area - North Park");
        floodGuideline.addPrecaution("Move to higher ground immediately");
        floodGuideline.addPrecaution("Do not attempt to cross flooded areas");
        floodGuideline.addPrecaution("Keep emergency kit handy");
        guidelines.add(floodGuideline);

        SafetyGuideline earthquakeGuideline = new SafetyGuideline("EARTHQUAKE", "Community Center - East Zone");
        earthquakeGuideline.addPrecaution("Drop, cover, and hold on");
        earthquakeGuideline.addPrecaution("Avoid windows and heavy objects");
        earthquakeGuideline.addPrecaution("Stay indoors until all-clear");
        guidelines.add(earthquakeGuideline);

        SafetyGuideline heatwaveGuideline = new SafetyGuideline("HEATWAVE", "Cooling Centers - Multiple Locations");
        heatwaveGuideline.addPrecaution("Stay hydrated");
        heatwaveGuideline.addPrecaution("Avoid strenuous activities");
        heatwaveGuideline.addPrecaution("Check on vulnerable individuals");
        guidelines.add(heatwaveGuideline);

        SafetyGuideline stormGuideline = new SafetyGuideline("STORM", "Underground Bunkers - South Zone");
        stormGuideline.addPrecaution("Stay indoors away from windows");
        stormGuideline.addPrecaution("Avoid using electrical appliances");
        stormGuideline.addPrecaution("Listen to emergency broadcasts");
        guidelines.add(stormGuideline);
    }

    public List<SafetyGuideline> getGuidelines() {
        return new ArrayList<>(guidelines);
    }

    public Map<String, List<String>> getContactInformation() {
        return new HashMap<>(contactInformation);
    }
}
