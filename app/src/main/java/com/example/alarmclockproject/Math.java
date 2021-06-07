package com.example.alarmclockproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class Math extends AppCompatActivity {

    private Button btnGo;
    private EditText solve;
    private String answer;
    private int sum;
    private boolean isSolved = false;
    private MediaPlayer mp;
    TextView tvTime, tvTask, tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        tvTime = findViewById(R.id.tv_time);
        tvTask = findViewById(R.id.tv_task);
        tvTask.setText(getTask());
        tvProgress = findViewById(R.id.tv_progress);
        solve = findViewById(R.id.et_solve);

        solve.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                answer = solve.getText().toString();
                if(answer!= null && Integer.parseInt(answer) == sum){
                    tvProgress.setText("True");
                    isSolved = true;
                    btnGo.setEnabled(true);

                } else {
                    tvProgress.setText("False. Try again!");

                }
            }
        });


        tvProgress.setText("Waiting...");
        btnGo = findViewById(R.id.btn_go_to_second);
        btnGo.setEnabled(false);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSolved){
                    Intent intent = new Intent (getApplicationContext(), RedSquare.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    public String getTask(){
        Random random = new Random();
        int x = random.nextInt(100);
        int y = random.nextInt(100);
        sum = x + y;
        return x + " + " + y;
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