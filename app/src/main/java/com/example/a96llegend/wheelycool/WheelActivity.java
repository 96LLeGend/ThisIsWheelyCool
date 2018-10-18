package com.example.a96llegend.wheelycool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.util.ArrayList;
import java.util.List;

public class WheelActivity extends AppCompatActivity {

    private static WheelView mWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        mWheelView = (WheelView)findViewById(R.id.wheel);

        //Pass options to the wheel
        String[] options = getIntent().getStringArrayExtra("options");
        List<String> optionList = new ArrayList<String>();
        for(int i = 0; i < options.length; i++){
            if(!options[i].equals("")) {
                optionList.add(options[i]);
            }
        }
        mWheelView.setOptions(optionList);
    }

    public void spin (View view) {
        RotateAnimation motor = new RotateAnimation(
                0, 259,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        motor.setDuration(500);
        motor.setRepeatCount(4);
        mWheelView.startAnimation(motor);
    }
}
