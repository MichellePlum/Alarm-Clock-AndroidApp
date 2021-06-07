package com.example.alarmclockproject;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener, View.OnClickListener {

    private TextView tv;
    private TimePicker timePicker;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    public static final int RECEIVER_REQ = 2801923;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSet = (Button) findViewById(R.id.btnSet);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        tv = (TextView)findViewById(R.id.tv);
        LinearLayout LL = (LinearLayout) findViewById(R.id.LL);
        timePicker = new TimePicker(this);
        LL.addView(timePicker);
        btnSet.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        timePicker.setOnTimeChangedListener(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSet: setAlarm(); break;
            case R.id.btnCancel: cancelAlarm(); break;
        }
    }


    @SuppressLint({"NewApi", "SetTextI18n"})
    private void setAlarm(){
        Intent intent = new Intent(this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, RECEIVER_REQ, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int second = 5, hour, minute;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        hour = timePicker.getHour();
        minute = timePicker.getMinute();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        String hourStr = String.valueOf(hour);
        String minuteStr = String.valueOf(minute);

        if(hour > 12)
            hourStr = String.valueOf(hour - 12);
        if(minute < 10)
            minuteStr = "0" + String.valueOf(minute);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (second * 1000), pendingIntent);
        tv.setText("Alarm will set in " + second + " seconds.\n                   Time: " + hourStr + ":" + minuteStr);

    }

    @SuppressLint("SetTextI18n")
    private void cancelAlarm(){
        if(pendingIntent != null)
            alarmManager.cancel(pendingIntent);
        tv.setText("ALARM DISABLED");
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        String format = "";

        if (hourOfDay == 0) {
            hourOfDay += 12;
            format = "AM";
        }
        else if(hourOfDay == 12){
            format = "PM";
        }
        else if(hourOfDay > 12){
            hourOfDay -= 12;
            format = "PM";
        }
    }
}