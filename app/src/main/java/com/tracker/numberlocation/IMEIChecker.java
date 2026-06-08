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

        // Sample IMEI data
        imeiDeviceMap.put("353658052037312", "Samsung Galaxy A10");
        imeiDeviceMap.put("490154203237518", "iPhone 12");
        imeiDeviceMap.put("860045068180286", "Xiaomi Redmi Note 9");
        imeiDeviceMap.put("869424053627268", "Nokia 3310");
        imeiDeviceMap.put("357430123456789", "OnePlus 9");
        imeiDeviceMap.put("352039063812407", "Samsung Galaxy S20");
        imeiDeviceMap.put("867961052903268", "Vivo Y20");
        imeiDeviceMap.put("868476064803689", "Oppo A53");

        // Sample IMEI to Active Number mappings
        imeiNumberMap.put("353658052037312", "01700000000");
        imeiNumberMap.put("490154203237518", "01811111111");
        imeiNumberMap.put("860045068180286", "01922222222");
        imeiNumberMap.put("869424053627268", "01933333333");
        imeiNumberMap.put("357430123456789", "01644444444");
        imeiNumberMap.put("352039063812407", "01755555555");
        imeiNumberMap.put("867961052903268", "01866666666");
        imeiNumberMap.put("868476064803689", "01977777777");
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

    public boolean validateIMEIChecksum(String imei) {
        if (!isValidIMEI(imei)) {
            return false;
        }
        
        int sum = 0;
        boolean isEven = false;

        for (int i = imei.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(imei.charAt(i));

            if (isEven) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            isEven = !isEven;
        }

        return (sum % 10 == 0);
    }
}
