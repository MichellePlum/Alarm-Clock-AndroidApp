package com.example.alarmclockproject;

import android.graphics.Paint;
import java.util.Random;

public class Square {
    int direction;
    float x;
    float y;
    final float width;
    final float height;
    Paint paint;
    int color;

    public Square(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 5;
        this.paint = new Paint();
        this.color = color;
        this.direction = new Random().nextInt(4);
        paint.setColor(color);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }
}
