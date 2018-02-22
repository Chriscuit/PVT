package com.humansignals.pvt;

import android.app.Activity;
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

    TextView counter;
    View button;

    long init, now, time;
    Handler handler;
    Runnable updater;
    int state = 0;

    int min = 3000;
    int max = 5000;
    int rand;

    int roundCap = 5;
    int round = 0;

    ArrayList<Long> data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        button = (View) findViewById(R.id.button);
        button.setOnClickListener(this);
        counter = (TextView) findViewById(R.id.counter);

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

                if (time > 15000) System.exit(0);
            }
        };

        startRandCount();
    }

    @Override
    public void onClick(View v) {
        round++;
        data.add(time);

        if (round == roundCap) {
            System.exit(0);
            saveData(data);
        }
        state = 0;
        handler.removeCallbacks(updater);
        counter.setText("");

        startRandCount();
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
