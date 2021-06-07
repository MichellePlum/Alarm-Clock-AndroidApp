package com.example.alarmclockproject;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import com.example.alarmclockproject.RedSquare;

public class Engine {
    private final Model model;
    private final Render render;
    private SurfaceHolder surfaceHolder;
    Canvas canvas;


    private volatile boolean stopped;
    long time = System.nanoTime();
    Runnable threadRunnable = new Runnable() {
        @Override
        public void run() {
            while (!stopped){
                if(surfaceHolder == null || (canvas = surfaceHolder.lockCanvas()) == null){
                    synchronized (Engine.this){
                        try{
                            Engine.this.wait();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                } else {
                    long timeElapsed = System.nanoTime() - time;
                    time = System.nanoTime();
                    model.update(timeElapsed, canvas);
                    render.draw(canvas, model);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    };

    public void stop(){
        this.stopped = true;
    }

    public Engine(SurfaceView surfaceView) {

        model = new Model();
        render = new Render();

        Thread engineThread = new Thread(threadRunnable, "EngineThread");
        engineThread.start();

        SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                Engine.this.surfaceHolder = holder;
                synchronized (Engine.this) {
                    Engine.this.notifyAll();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                Engine.this.surfaceHolder = null;
            }
        };
        surfaceView.getHolder().addCallback(callback);
    }

    public boolean check(int touchX, int touchY){
        Square rect = model.getRedRect();
        int tagX = (int) rect.x;
        int tagY = (int) rect.y;
        Log.d("location", ("check location! -  x: " + (tagX + 25) + ", y: " + (tagY + 25) + "\n" + "touchX = " + touchX + ", touchY = " + touchY));
        return touchX >= tagX - 50 && touchX <= tagX + 100 && touchY - RedSquare.sizeAB >= tagY - 50 && touchY - RedSquare.sizeAB <= tagY + 100;
    }
}