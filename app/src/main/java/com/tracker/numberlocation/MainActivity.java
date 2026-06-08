package com.tracker.numberlocation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber, inputIMEI;
    private Button searchNumberBtn, searchIMEIBtn;
    private TextView resultText;
    private OperatorDatabase operatorDB;
    private IMEIChecker imeiChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        inputNumber = findViewById(R.id.inputNumber);
        inputIMEI = findViewById(R.id.inputIMEI);
        searchNumberBtn = findViewById(R.id.searchNumberBtn);
        searchIMEIBtn = findViewById(R.id.searchIMEIBtn);
        resultText = findViewById(R.id.resultText);

        // Initialize Database and Checker
        operatorDB = new OperatorDatabase();
        imeiChecker = new IMEIChecker();

        // Search by Number Button Click
        searchNumberBtn.setOnClickListener(v -> {
            String number = inputNumber.getText().toString().trim();
            if (number.isEmpty()) {
                Toast.makeText(MainActivity.this, "নম্বর লিখুন", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Utils.isValidPhoneNumber(number)) {
                Toast.makeText(MainActivity.this, "সঠিক নম্বর লিখুন", Toast.LENGTH_SHORT).show();
                return;
            }
            searchByNumber(number);
        });

        // Search by IMEI Button Click
        searchIMEIBtn.setOnClickListener(v -> {
            String imei = inputIMEI.getText().toString().trim();
            if (imei.isEmpty()) {
                Toast.makeText(MainActivity.this, "IMEI লিখুন", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Utils.isValidIMEI(imei)) {
                resultText.setText("❌ IMEI অবৈধ (15 সংখ্যা হতে হবে)");
                return;
            }
            searchByIMEI(imei);
        });
    }

    private void searchByNumber(String number) {
        String prefix = Utils.extractPrefix(number);
        String operator = operatorDB.getOperator(prefix);
        String location = operatorDB.getLocation(prefix);
        String status = operatorDB.getStatus(number);

        String result = "📱 নম্বর: " + number + "\n\n" +
                "🏢 অপারেটর: " + operator + "\n\n" +
                "📍 এলাকা: " + location + "\n\n" +
                "✅ স্ট্যাটাস: " + status;

        resultText.setText(result);
    }

    private void searchByIMEI(String imei) {
        String cleanIMEI = imei.replaceAll("[^0-9]", "");
        
        if (!imeiChecker.isValidIMEI(cleanIMEI)) {
            resultText.setText("❌ IMEI অবৈধ\n\nIMEI হবে 15 সংখ্যা");
            return;
        }

        String deviceInfo = imeiChecker.getDeviceInfo(cleanIMEI);
        String activeNumber = imeiChecker.getActiveNumber(cleanIMEI);
        
        if (activeNumber.equals("সংযুক্ত নেই")) {
            resultText.setText("❌ এই IMEI ডাটাবেসে নেই");
            return;
        }
        
        String prefix = Utils.extractPrefix(activeNumber);
        String operator = operatorDB.getOperator(prefix);
        String location = operatorDB.getLocation(prefix);

        String result = "📱 IMEI: " + cleanIMEI + "\n\n" +
                "📞 ডিভাইস: " + deviceInfo + "\n\n" +
                "🔗 এক্টিভ নম্বর: " + activeNumber + "\n\n" +
                "🏢 অপারেটর: " + operator + "\n\n" +
                "📍 এলাকা: " + location + "\n\n" +
                "✅ স্ট্যাটাস: সক্রিয়";

        resultText.setText(result);
    }
}
