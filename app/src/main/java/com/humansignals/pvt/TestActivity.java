package com.humansignals.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

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

    static final int REQUEST_CODE = 1;
    int testLength = 180000;
    int min = 5000;
    int max = 30000;
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
                    //saveData(data, System.currentTimeMillis());

                    Intent intent = new Intent(TestActivity.this, SurveyActivity.class);

                    startActivityForResult(intent, REQUEST_CODE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check which request we're responding to
        if (requestCode == REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                int result=data.getIntExtra("alertness", 0);
                saveData(result);
                finish();
                // Do something with the contact here (bigger example below)
            }
        }

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

    /* "data" has the list of times and completionTime is in ms */
//    public void saveData(ArrayList<Long> data, Long completionTime) {
//
//        ((DataObject)getApplication()).setClickTimes(data);
//        ((DataObject)getApplication()).setCompletionTime(completionTime);
//
//    }

    public void saveData(int alertness){
        String fileName = "PVT_data.txt";
        StringBuilder aggregateBuilder = new StringBuilder();

        // distance, length error, duration, speed, timestamp, category
        for(Long clickTime : data) {
            aggregateBuilder.append(Long.toString(clickTime) + "\t");
        }
        aggregateBuilder.append(Long.toString(System.currentTimeMillis()) + "\t");
        aggregateBuilder.append(String.valueOf(alertness) + "\t");

        String data = aggregateBuilder.toString();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PVT/" ;
        Log.d("SAVE PATH", path);
        try {
            new File(path).mkdir();
            File file = new File(path + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            if (!file.exists()) {
                if(!file.createNewFile()) {
                    Toast.makeText(TestActivity.this, "Error creating file", Toast.LENGTH_SHORT).show();
                }
            }
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }
}
