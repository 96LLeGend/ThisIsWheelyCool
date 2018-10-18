package com.example.a96llegend.wheelycool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText option1;
    private EditText option2;
    private EditText option3;
    private EditText option4;
    private EditText option5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set default option from last entry
        option1 = (EditText)findViewById(R.id.option1);
        option2 = (EditText)findViewById(R.id.option2);
        option3 = (EditText)findViewById(R.id.option3);
        option4 = (EditText)findViewById(R.id.option4);
        option5 = (EditText)findViewById(R.id.option5);
        addOptions();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addOptions();
    }

    //Button for to the wheel
    public void toWheel (View view){
        String[] options = {"", "", "", "", ""};

        //Save all the user's input and add to array for passing
        putOption(1, option1.getText().toString());
        options[0] = option1.getText().toString();
        putOption(2, option2.getText().toString());
        options[1] = option2.getText().toString();
        putOption(3, option3.getText().toString());
        options[2] = option3.getText().toString();
        putOption(4, option4.getText().toString());
        options[3] = option4.getText().toString();
        putOption(5, option5.getText().toString());
        options[4] = option5.getText().toString();

        //To the wheel
        Intent intent = new Intent(this, WheelActivity.class);
        intent.putExtra("options", options);
        startActivity(intent);
    }

    //Check if a option has been edited, if so, display that value as default value
    private void addOptions(){
        if(getOption(1) != null){
            option1.setText(getOption(1).toString());
        }
        if(getOption(2) != null){
            option2.setText(getOption(2).toString());
        }
        if(getOption(3) != null){
            option3.setText(getOption(3).toString());
        }
        if(getOption(4) != null){
            option4.setText(getOption(4).toString());
        }
        if(getOption(5) != null){
            option5.setText(getOption(5).toString());
        }
    }

    //Add option to the list
    private void putOption(int optionNumber, String option){
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("OptionList", MODE_PRIVATE).edit();
        if(optionNumber == 1) {
            editor.putString("Option1", option);
        } else  if(optionNumber == 2) {
            editor.putString("Option2", option);
        } else if(optionNumber == 3) {
            editor.putString("Option3", option);
        } else  if(optionNumber == 4) {
            editor.putString("Option4", option);
        } else {
            editor.putString("Option5", option);
        }
        editor.apply();
    }

    //Get option from the list
    private String getOption(int optionNumber){
        SharedPreferences prefs = getSharedPreferences("OptionList", MODE_PRIVATE);
        if(optionNumber == 1) {
            return prefs.getString("Option1", null);
        } else  if(optionNumber == 2) {
            return prefs.getString("Option2", null);
        } else if(optionNumber == 3) {
            return prefs.getString("Option3", null);
        } else  if(optionNumber == 4) {
            return prefs.getString("Option4", null);
        } else {
            return prefs.getString("Option5", null);
        }
    }
}
