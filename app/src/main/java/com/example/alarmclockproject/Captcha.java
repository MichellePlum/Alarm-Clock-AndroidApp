package com.example.alarmclockproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Captcha extends AppCompatActivity implements View.OnClickListener {

    private final int[] idArray = {0, R.id.Keira1, R.id.Keira2, R.id.Keira3, R.id.Keira4, R.id.Keira5,
            R.id.Keira6, R.id.Keira7, R.id.Keira8, R.id.Keira9, R.id.Natalie1, R.id.Natalie2,
            R.id.Natalie3, R.id.Natalie4,  R.id.Natalie5, R.id.Natalie6, R.id.Natalie7 };

    private TextView finishText;
    private boolean open = false;
    private boolean openTarget = false;
    private int countAnswers = 0;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);

        finishText = (TextView)findViewById(R.id.finishText);

        for (int i = 1; i <= 16; i++) {
            ImageButton button = (ImageButton)findViewById(idArray[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 1; i <= 16; i++){
            if (v.getId() == idArray[i]) {
                checkButton(v);
            }
        }

        if(v.getId() == R.id.btnCheck)
            checkAnswer();
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        countAnswers = 7;        // משחק הזה לא עובד! רק לדוגמה!!! יש בעיה עם לחיצות. חסר listener עבור סיום אירוע לחיצה. זה counter זמני לצורך הפעלת intent
        if(countAnswers == 7){
            finishText.setText("Yes! It's correct answer!");
            Intent intent = new Intent(getApplicationContext(), Picture.class);
            startActivity(intent);
            finish();
        }
        else{
            countAnswers = 0;
            finishText.setText("Wooops! Wrong answer, try again.");
        }
    }


    @SuppressLint("NonConstantResourceId")
    private void checkButton(View v) {

        switch(v.getId()) {
            case R.id.Keira1:
                if(!open){
                    v.setBackgroundResource(R.drawable.k10check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k10);
                    open = false;
                } break;

            case R.id.Keira2:
                if(!open){
                    v.setBackgroundResource(R.drawable.k2check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k2);
                    open = false;
                } break;

            case R.id.Keira3:
                if(!open){
                    v.setBackgroundResource(R.drawable.k3check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k3);
                    open = false;
                } break;

            case R.id.Keira4:
                if(!open){
                    v.setBackgroundResource(R.drawable.k4check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k4);
                    open = false;
                } break;


            case R.id.Keira5:
                if(!open){
                    v.setBackgroundResource(R.drawable.k5check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k5);
                    open = false;
                } break;


            case R.id.Keira6:
                if(!open){
                    v.setBackgroundResource(R.drawable.k6check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k6);
                    open = false;
                } break;

            case R.id.Keira7:
                if(!open){
                    v.setBackgroundResource(R.drawable.k7check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k7);
                    open = false;
                } break;

            case R.id.Keira8:
                if(!open){
                    v.setBackgroundResource(R.drawable.k8check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k8);
                    open = false;
                } break;

            case R.id.Keira9:
                if(!open){
                    v.setBackgroundResource(R.drawable.k9check);
                    open = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.k9);
                    open = false;
                } break;

            case R.id.Natalie1:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n1check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n1);
                    openTarget = false;
                } break;

            case R.id.Natalie2:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n2check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n2);
                    openTarget = false;
                } break;

            case R.id.Natalie3:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n3check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n3);
                    openTarget = false;
                } break;

            case R.id.Natalie4:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n4check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n4);
                    open = false;
                } break;

            case R.id.Natalie5:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n5check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n5);
                    openTarget = false;
                } break;

            case R.id.Natalie6:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n6check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n6);
                    openTarget = false;
                } break;

            case R.id.Natalie7:
                if(!openTarget){
                    v.setBackgroundResource(R.drawable.n7check);
                    openTarget = true;
                }
                else {
                    v.setBackgroundResource(R.drawable.n7);
                    openTarget = false;

                } break;
        }
        Log.d("count", "checkCount: " + countAnswers);
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