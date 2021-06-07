package com.example.alarmclockproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Render {
    Paint paint;

    public Render(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void draw(Canvas canvas, Model model){
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        paint.setColor(Color.GREEN);

        for(Square rect: model.getSquares()){
            canvas.drawRect(rect.x, rect.y, rect.x + 50, rect.y + 50, rect.paint);
        }
    }
}
