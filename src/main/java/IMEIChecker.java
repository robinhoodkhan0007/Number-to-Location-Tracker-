package com.tracker.numberlocation;

import java.util.HashMap;
import java.util.Map;

public class IMEIChecker {

    private Map<String, String> imeiDeviceMap;
    private Map<String, String> imeiNumberMap;

    public IMEIChecker() {
        initializeIMEIDatabase();
    }

    private void initializeIMEIDatabase() {
        imeiDeviceMap = new HashMap<>();
        imeiNumberMap = new HashMap<>();

        imeiDeviceMap.put("353658052037312", "Samsung Galaxy A10");
        imeiDeviceMap.put("490154203237518", "iPhone 12");
        imeiDeviceMap.put("860045068180286", "Xiaomi Redmi Note 9");
        imeiDeviceMap.put("869424053627268", "Nokia 3310");

        imeiNumberMap.put("353658052037312", "01700000000");
        imeiNumberMap.put("490154203237518", "01811111111");
        imeiNumberMap.put("860045068180286", "01922222222");
        imeiNumberMap.put("869424053627268", "01933333333");
    }

    public boolean isValidIMEI(String imei) {
        if (imei == null || imei.length() != 15) {
            return false;
        }
        return imei.matches("\\d{15}");
    }

    public String getDeviceInfo(String imei) {
        return imeiDeviceMap.getOrDefault(imei, "অজানা ডিভাইস");
    }

    public String getActiveNumber(String imei) {
        return imeiNumberMap.getOrDefault(imei, "সংযুক্ত নেই");
    }
}
