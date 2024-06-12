package com.example.lab2_java;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Bar implements View.OnClickListener {
    private Context content;
    public Bar(SecondActivity secondActivity) {
        this.content = secondActivity;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(content, "button4 clicked", Toast.LENGTH_LONG).show();
    }
}
