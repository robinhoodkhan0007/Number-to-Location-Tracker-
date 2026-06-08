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

        operatorMap.put("0170", "Grameenphone");
        operatorMap.put("0171", "Grameenphone");
        operatorMap.put("0172", "Grameenphone");
        operatorMap.put("0181", "Banglalink");
        operatorMap.put("0182", "Banglalink");
        operatorMap.put("0191", "Robi");
        operatorMap.put("0192", "Robi");
        operatorMap.put("0193", "Robi");
        operatorMap.put("0151", "Teletalk");
        operatorMap.put("0152", "Teletalk");
        operatorMap.put("0161", "Airtel");
        operatorMap.put("0162", "Airtel");
    }

    private void initializeLocations() {
        locationMap = new HashMap<>();
        locationMap.put("0170", "ঢাকা");
        locationMap.put("0171", "ঢাকা");
        locationMap.put("0172", "চট্টগ্রাম");
        locationMap.put("0181", "ঢাকা");
        locationMap.put("0182", "সিলেট");
        locationMap.put("0191", "রাজশাহী");
        locationMap.put("0192", "খুলনা");
        locationMap.put("0193", "বরিশাল");
        locationMap.put("0151", "ঢাকা");
        locationMap.put("0152", "চট্টগ্রাম");
        locationMap.put("0161", "ঢাকা");
        locationMap.put("0162", "সিলেট");
    }

    public String getOperator(String number) {
        if (number == null || number.length() < 4) {
            return "অজানা";
        }

        String prefix = number.substring(0, 4);
        return operatorMap.getOrDefault(prefix, "অজানা");
    }

    public String getLocation(String number) {
        if (number == null || number.length() < 4) {
            return "অজানা";
        }

        String prefix = number.substring(0, 4);
        return locationMap.getOrDefault(prefix, "অজানা");
    }

    public String getStatus(String number) {
        return "সক্রিয়";
    }
}
