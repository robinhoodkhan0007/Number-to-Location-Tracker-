package com.tracker.numberlocation;

import java.util.HashMap;
import java.util.Map;

public class OperatorDatabase {

    private Map<String, String> operatorMap;
    private Map<String, String> locationMap;

    public OperatorDatabase() {
        initializeOperators();
        initializeLocations();
    }

    private void initializeOperators() {
        operatorMap = new HashMap<>();

        // Grameenphone
        operatorMap.put("0170", "Grameenphone");
        operatorMap.put("0171", "Grameenphone");
        operatorMap.put("0172", "Grameenphone");

        // Banglalink
        operatorMap.put("0181", "Banglalink");
        operatorMap.put("0182", "Banglalink");

        // Robi
        operatorMap.put("0191", "Robi");
        operatorMap.put("0192", "Robi");
        operatorMap.put("0193", "Robi");

        // Teletalk
        operatorMap.put("0151", "Teletalk");
        operatorMap.put("0152", "Teletalk");

        // Airtel
        operatorMap.put("0161", "Airtel");
        operatorMap.put("0162", "Airtel");
    }

    private void initializeLocations() {
        locationMap = new HashMap<>();
        
        // Grameenphone locations
        locationMap.put("0170", "ঢাকা");
        locationMap.put("0171", "ঢাকা");
        locationMap.put("0172", "চট্টগ্রাম");
        
        // Banglalink locations
        locationMap.put("0181", "ঢাকা");
        locationMap.put("0182", "সিলেট");
        
        // Robi locations
        locationMap.put("0191", "রাজশাহী");
        locationMap.put("0192", "খুলনা");
        locationMap.put("0193", "বরিশাল");
        
        // Teletalk locations
        locationMap.put("0151", "ঢাকা");
        locationMap.put("0152", "চট্টগ্রাম");
        
        // Airtel locations
        locationMap.put("0161", "ঢাকা");
        locationMap.put("0162", "সিলেট");
    }

    public String getOperator(String prefix) {
        if (prefix == null || prefix.length() < 4) {
            return "অজানা";
        }
        return operatorMap.getOrDefault(prefix, "অজানা");
    }

    public String getLocation(String prefix) {
        if (prefix == null || prefix.length() < 4) {
            return "অজানা";
        }
        return locationMap.getOrDefault(prefix, "অজানা");
    }

    public String getStatus(String number) {
        return "সক্রিয়";
    }
}
