package com.example.alarmclockproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("check", "Receiver ON");

        Intent intentSec = new Intent(context, Captcha.class);
        intentSec.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentSec);
    }
}
