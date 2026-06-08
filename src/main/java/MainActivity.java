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

        inputNumber = findViewById(R.id.inputNumber);
        inputIMEI = findViewById(R.id.inputIMEI);
        searchNumberBtn = findViewById(R.id.searchNumberBtn);
        searchIMEIBtn = findViewById(R.id.searchIMEIBtn);
        resultText = findViewById(R.id.resultText);

        operatorDB = new OperatorDatabase();
        imeiChecker = new IMEIChecker();

        searchNumberBtn.setOnClickListener(v -> {
            String number = inputNumber.getText().toString().trim();
            if (number.isEmpty()) {
                Toast.makeText(MainActivity.this, "নম্বর লিখুন", Toast.LENGTH_SHORT).show();
                return;
            }
            searchByNumber(number);
        });

        searchIMEIBtn.setOnClickListener(v -> {
            String imei = inputIMEI.getText().toString().trim();
            if (imei.isEmpty()) {
                Toast.makeText(MainActivity.this, "IMEI লিখুন", Toast.LENGTH_SHORT).show();
                return;
            }
            searchByIMEI(imei);
        });
    }

    private void searchByNumber(String number) {
        String operator = operatorDB.getOperator(number);
        String location = operatorDB.getLocation(number);
        String status = operatorDB.getStatus(number);

        String result = "📱 নম্বর: " + number + "\n" +
                "🏢 অপারেটর: " + operator + "\n" +
                "📍 এলাকা: " + location + "\n" +
                "✅ স্ট্যাটাস: " + status;

        resultText.setText(result);
    }

    private void searchByIMEI(String imei) {
        if (!imeiChecker.isValidIMEI(imei)) {
            resultText.setText("❌ IMEI অবৈধ");
            return;
        }

        String deviceInfo = imeiChecker.getDeviceInfo(imei);
        String activeNumber = imeiChecker.getActiveNumber(imei);
        String operator = operatorDB.getOperator(activeNumber);
        String location = operatorDB.getLocation(activeNumber);

        String result = "📱 IMEI: " + imei + "\n" +
                "📞 ডিভাইস: " + deviceInfo + "\n" +
                "🔗 এক্টিভ নম্বর: " + activeNumber + "\n" +
                "🏢 অপারেটর: " + operator + "\n" +
                "📍 এলাকা: " + location + "\n" +
                "✅ স্ট্যাটাস: সক্রিয়";

        resultText.setText(result);
    }
}
