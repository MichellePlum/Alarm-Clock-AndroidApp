package com.example.alarmclockproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class Picture extends AppCompatActivity implements View.OnClickListener {

    private final int[] dog = {0,
            R.id.d1, R.id.d2, R.id.d3,
            R.id.d4, R.id.d5, R.id.d6,
            R.id.d7, R.id.d8, R.id.d9};
    private final int[] draws = {0,
            R.drawable.d1, R.drawable.d2, R.drawable.d3,
            R.drawable.d4, R.drawable.d5, R.drawable.d6,
            R.drawable.d7, R.drawable.d8, R.drawable.d9};
    private MediaPlayer mp;
    int correct = 0, choice1 = 0, choice2 = 0;
    boolean done1 = false, done2 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        for (int i = 1; i <= 9; i++) {
            ImageView btn = findViewById(dog[i]);
            btn.setOnClickListener(this);
        }
    }


    public void handleButton(int a) {
        if (choice1 == 0) {
            choice1 = a;
        } else if (choice2 == 0) {
            choice2 = a;
            if (choice1 != choice2) {
                int tmp = choice1;

                if (((choice1 == 1 && choice2 == 5) || (choice2 == 1 && choice1 == 5)) && !done1) {
                    ((ImageView) findViewById(dog[choice2])).setImageResource((draws[choice2]));
                    ((ImageView) findViewById(dog[choice1])).setImageResource((draws[choice1]));
                    correct++;
                    done1 = true;
                } else if (((choice1 == 7 && choice2 == 9) || (choice2 == 7 && choice1 == 9)) && !done2) {
                    ((ImageView) findViewById(dog[choice2])).setImageResource((draws[choice2]));
                    ((ImageView) findViewById(dog[choice1])).setImageResource((draws[choice1]));
                    correct++;
                    done2 = true;
                } else {
                    Toast.makeText(this, "Try again!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            choice1 = 0;
            choice2 = 0;
        }


        if (correct == 2) {
             Intent win = new Intent (getApplicationContext(), Math.class);
             startActivity(win);
             finish();
        }

    }

    @Override
    public void onClick(View v) {
        for (int i = 1; i <= 9; i++) {
            if (v.getId() == dog[i]) {
                //found the pressed button
                handleButton(i);    // i is the button number
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        mp = MediaPlayer.create(this, R.raw.alarmsound);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(mp.isPlaying())
            mp.stop();
        mp.release();
    }

    @Override
    public void onBackPressed()
    {
        int second = 2;

        Log.d("uuuu", "onBackPressed ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (second * 1000), pendingIntent);
        finish();
    }
}
