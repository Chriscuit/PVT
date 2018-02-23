package com.humansignals.pvt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


// make count box static size
// make box disappear and reappear
// make it change color with click
// holder number longer with click
// create intro/outro screen
// let user choose when the process begins with initial button?
// make app record other data?
// add aspect of swiping in particular directions?
// have stage #/# over the counter
// have it make a sound on click
// add better comments?

/* TO DO
* 1) Main menu screen where there are two buttons
* - Run Test
* - log history
* 3) At end of test ask about alertness
* 4) On history page, show...
* - results
* - time of completion
* - response to the dialogue
* */

/*
* Probably add a page where you can view the rules and stuff before proceeding to the actual test, maybe and information button in the corner?
* */

public class MainActivity extends AppCompatActivity {

    View button2;
    View button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (View) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        button3 = (View) findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}


