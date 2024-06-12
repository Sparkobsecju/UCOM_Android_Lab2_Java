package com.example.lab2_java;

import static android.content.Intent.ACTION_VIEW;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void doActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }

    public void doPhone(View view) {
        checkCallPhonePermission();
        // realCallPhone();
    }

    private static final int CALL_PHONE_PERMISSION_CHECK = 1234;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_CHECK && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            realCallPhone();
        } else {
            TextView t = findViewById(R.id.textView);
            t.setText("需要去重置權限，才能再進行通話測試");
        }
    }

    private void checkCallPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this,"need to check permission, but how?",Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                showPromptDialog();
            } else {
                askForPermission();
            }
        } else {
            realCallPhone();
        }
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_CHECK);
    }

    private void showPromptDialog() {
        new AlertDialog.Builder(this).
                setTitle("需要撥打電話").
                setMessage("因為需要驗証").
                setPositiveButton("OK", (d, w) -> askForPermission()).
                setNegativeButton("No", (d, w) -> finish()).
                show();
    }

    private void realCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:021234567"));
        try {

            startActivity(intent);
        } catch (SecurityException se) {
            TextView t = findViewById(R.id.textView);
            t.setText(String.format("沒有權限, 訊息是%s", se.getCause().toString()));
        }
    }


    public void doYahoo() {
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(Uri.parse("http://www.yahoo.com.tw"));
        startActivity(intent);
    }
}