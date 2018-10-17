package com.example.a96llegend.wheelycool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WheelActivity extends AppCompatActivity {

    private static String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        options = getIntent().getStringArrayExtra("options");
    }


}
