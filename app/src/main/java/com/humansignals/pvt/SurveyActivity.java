package com.humansignals.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by christophergill on 2/23/18.
 */

public class SurveyActivity extends Activity {

    CheckBox box1, box2, box3, box4, box5;
    int alertness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.survey_activity);

        box1 = (CheckBox) findViewById(R.id.checkBox3);
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alertness = 1;
               toMain();

            }
        });

        box2 = (CheckBox) findViewById(R.id.checkBox4);
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = 2;
                toMain();

            }
        });

        box3 = (CheckBox) findViewById(R.id.checkBox5);
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = 3;
                toMain();

            }
        });

        box4 = (CheckBox) findViewById(R.id.checkBox6);
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = 4;
                toMain();

            }
        });

        box5 = (CheckBox) findViewById(R.id.checkBox7);
        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertness = 5;
                toMain();

            }
        });
    }

    void toMain() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("alertness",alertness);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
}
