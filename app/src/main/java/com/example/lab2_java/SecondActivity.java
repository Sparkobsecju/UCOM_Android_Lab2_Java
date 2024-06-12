package com.example.lab2_java;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new Foo());
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new Bar(this));

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "button5 clicked", Toast.LENGTH_LONG).show();
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener( v -> {
            Toast.makeText(this, "button6 clicked", Toast.LENGTH_LONG).show();
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener( v -> Toast.makeText(this, "button7 clicked", Toast.LENGTH_LONG).show() );

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> {
            v.setBackgroundColor(Color.GREEN);
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(v -> {
            Button btn = (Button) v;
            btn.setText("1234567");
        });
    }

    @Override
    public void onClick(View v) {
        String message = "";
//        switch (v.getId()) {
//            case R.id.button1:
//                message = "button1 clicked";
//                break;
//            case R.id.button2:
//                message = "button2 clicked";
//                break;
//        }
        if (v.getId() == R.id.button1) {
            message = "button1 clicked";
        } else if (v.getId() == R.id.button2) {
            message = "button2 clicked";
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class Foo implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(SecondActivity.this, "button3 clicked", Toast.LENGTH_LONG).show();
        }
    }
}
