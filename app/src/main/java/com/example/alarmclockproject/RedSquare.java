package com.example.alarmclockproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.alarmclockproject.Engine;


public class RedSquare extends AppCompatActivity {

    Engine engine;
    MediaPlayer mp;
    public static int sizeAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_square);
        SurfaceView surface = findViewById(R.id.surface);
        engine = new Engine(surface);

        surface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });

        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            int sizeAB = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN && engine.check((int)event.getX(),(int) event.getY())){
            Intent intent = new Intent(getApplicationContext(), EndScreen.class);
            startActivity(intent);
            finish();
        }
        return true;
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
