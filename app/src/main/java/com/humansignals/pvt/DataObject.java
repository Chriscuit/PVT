package com.humansignals.pvt;

import android.app.Application;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by christophergill on 2/23/18.
 */

public class DataObject extends Application {

    ArrayList<Long> clickTimes;
    Long completionTime;
    String alertFeels;

    StringBuilder aggregateBuilder = new StringBuilder();

    DataObject() {}

    public void setClickTimes(ArrayList<Long> clickTimes) {
        this.clickTimes = clickTimes;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }

    public void setAlertFeels(String alertFeels) {
        this.alertFeels = alertFeels;
    }


//    public void writeOut() {
//
//        for(Long clickTime : clickTimes) {
//            aggregateBuilder.append("Click times:" + Long.toString(clickTime) + " ");
//        }
//
//        aggregateBuilder.append("Completion Time: " + Long.toString(completionTime));
//        aggregateBuilder.append("Alertness: " + alertFeels);
//
//        String text = aggregateBuilder.toString();
//
//        File logFile = new File("./log.file");
//        if (!logFile.exists())
//        {
//            try
//            {
//                logFile.createNewFile();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        try
//        {
//            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
//            buf.append(text);
//            buf.newLine();
//            buf.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void saveData(){
        String fileName = "cognitive_data.txt";
        // distance, length error, duration, speed, timestamp, category
        for(Long clickTime : clickTimes) {
            aggregateBuilder.append(Long.toString(clickTime) + "\t");
        }
        aggregateBuilder.append(Long.toString(completionTime) + "\t");
        aggregateBuilder.append(alertFeels + "\t");

        String data = aggregateBuilder.toString();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/customLock/" ;
        Log.d("SAVE PATH", path);
        try {
            new File(path).mkdir();
            File file = new File(path + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            if (!file.exists()) {
                if(!file.createNewFile()) {
                    Toast.makeText(DataObject.this, "Error creating file", Toast.LENGTH_SHORT).show();
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
