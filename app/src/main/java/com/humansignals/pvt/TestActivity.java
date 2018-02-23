package com.humansignals.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by christophergill on 2/22/18.
 */

public class TestActivity extends Activity implements View.OnClickListener {

    TextView remaining;
    TextView counter;
    View button;

    int seconds, minutes;
    long timeLeft, elapsed, testStart, init, now, time;
    Handler handler;
    Handler handler2;
    Runnable updater;
    Runnable ender;
    int state = 0;

    int testLength = 30000;
    int min = 5000;
    int max = 10000;
    int rand;

    ArrayList<Long> data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        button = (View) findViewById(R.id.button);
        button.setOnClickListener(this);
        counter = (TextView) findViewById(R.id.counter);
        remaining = (TextView) findViewById(R.id.remaining);

        handler = new Handler();

        updater = new Runnable() {
            @Override
            public void run() {
                if (state == 0) {
                    init = System.currentTimeMillis();
                    state = 1;
                }
                now = System.currentTimeMillis();
                time = now - init;
                counter.setText("" + time);
                handler.postDelayed(this, 30);

                if (time >= 15000) finish();
            }
        };

        handler2 = new Handler();

        ender = new Runnable() {
            @Override
            public void run() {
                now = System.currentTimeMillis();
                elapsed = now - testStart;
                timeLeft = testLength - elapsed;
                if (elapsed >= testLength) {
                    saveData(data);

                    Intent intent = new Intent(TestActivity.this, SurveyActivity.class);
                    startActivity(intent);
                } else {

                    seconds = (int) ((timeLeft / 1000) % 60);
                    minutes = (int) ((timeLeft / 1000) / 60);

                    remaining.setText(minutes + ":" + seconds);
                    handler2.postDelayed(this, 30);
                }
            }
        };

        testStart = System.currentTimeMillis();

        handler2.post(ender);
        startRandCount();
    }

    @Override
    public void onClick(View v) {
        if(state == 1) {
            data.add(time);
            state = 0;
            handler.removeCallbacks(updater);

            counter.setText("");

            startRandCount();
        }
    }

    public void startRandCount() {
        Random r = new Random();
        rand = r.nextInt(max - min) + min;
        handler.postDelayed(updater, rand);
    }

    public void saveData(ArrayList<Long> data) {
        // do something here
    }

}
