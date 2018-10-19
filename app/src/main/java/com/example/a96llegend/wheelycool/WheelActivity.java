package com.example.a96llegend.wheelycool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class WheelActivity extends AppCompatActivity {

    private static WheelView mWheelView;
    private static ImageView mPointerView;
    private static float lastStopAngle = 0;
    private static int numberOfOptions;

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
        numberOfOptions = optionList.size();

        //Reset wheel's rotation
        lastStopAngle = 0;
        mWheelView.setRotation(0);

        //If no options, don't show pointer
        mPointerView = (ImageView) findViewById(R.id.pointerView);
        if(numberOfOptions == 0){
            mPointerView.setVisibility(View.INVISIBLE);
        } else {
            mPointerView.setVisibility(View.VISIBLE);
        }
    }

    public void spin (View view) {
        if(numberOfOptions > 1) { //Don't spin unless there is more than 1 options
            //Determine final stop angle, between 2 to 3 rotation
            float finalAngle = Double.valueOf((Math.random() * (1080 - 720)) + 720).floatValue();

            //Start Animation
            RotateAnimation motor = new RotateAnimation(
                    lastStopAngle, (lastStopAngle + finalAngle),
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            motor.setDuration(3000);
            motor.setRepeatCount(0);
            motor.setFillAfter(true);
            mWheelView.startAnimation(motor);

            //Record the last stop position of the wheel for next turn
            if (lastStopAngle + finalAngle > Float.MAX_VALUE) {
                lastStopAngle = 0;
            } else {
                lastStopAngle = lastStopAngle + finalAngle;
            }
        }
    }
}
