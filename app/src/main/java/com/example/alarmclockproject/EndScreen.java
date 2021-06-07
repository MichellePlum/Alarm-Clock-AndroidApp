package com.example.alarmclockproject;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EndScreen extends AppCompatActivity implements View.OnClickListener {
    TextView tv_time;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        tv_time = findViewById(R.id.tvEnd);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String formattedTime = df.format(calendar.getTime());
        tv_time.setText(formattedTime);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mp = MediaPlayer.create(this, R.raw.happy);
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
    public void onClick(View v) {
        finish();
    }
}