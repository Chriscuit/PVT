package com.humansignals.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by christophergill on 2/23/18.
 */

public class SurveyActivity extends Activity {

    CheckBox box1, box2, box3, box4, box5;
    String alertness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.survey_activity);

        box1 = (CheckBox) findViewById(R.id.checkBox3);
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alertness = "Very Unalert";
               toMain();
            }
        });

        box2 = (CheckBox) findViewById(R.id.checkBox4);
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = "Unalert";
                toMain();
            }
        });

        box3 = (CheckBox) findViewById(R.id.checkBox5);
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = "Neutral";
                toMain();
            }
        });

        box4 = (CheckBox) findViewById(R.id.checkBox6);
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = "Alert";
                toMain();
            }
        });

        box5 = (CheckBox) findViewById(R.id.checkBox7);
        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = "Very Alert";
                toMain();
            }
        });
    }

    void toMain() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SurveyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
}
