package com.example.alarmclockproject;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    int canvasWidth;
    int canvasHeight;
    int canvasEx = 0;
    public static final float MOTION_SPEED = 0.0000006f;

    ArrayList<Square> squares = new ArrayList<>();
    public static final String RED = "RED";

    long time = 0;
    int i = 0;

    public void update(long elapsedTime, Canvas canvas) {
        time++;
        canvasEx++;
        if (canvasEx == 5) {
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
            createAll();
        }


        Random r = new Random();
        for (int j = 0; j < squares.size(); j++) {

            if (time > 75) {
                i = r.nextInt(4);
                squares.get(j).direction = i;
                time = 0;
            }
            if (squares.get(j).y <= 100) {
                squares.get(j).direction = 3;
            }
            if (squares.get(j).x <= 10) {
                squares.get(j).direction = 2;
            }
            if (squares.get(j).y >= canvas.getHeight() - 50) {
                squares.get(j).direction = 0;
            }
            if (squares.get(j).x >= canvas.getWidth() - 50) {
                squares.get(j).direction = 1;
            }

            move(squares.get(j).direction, squares.get(j), elapsedTime);
        }
    }

    public void move(int i, Square square, long elapsedTime) {
        if (i == 0) {
            square.y -= elapsedTime * MOTION_SPEED;
        }
        if (i == 2) {
            square.x += elapsedTime * MOTION_SPEED;
        }
        if (i == 1) {
            square.x -= elapsedTime * MOTION_SPEED;
        }
        if (i == 3) {
            square.y += elapsedTime * MOTION_SPEED;
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    int[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.CYAN, Color.BLACK};

    public Square create(String color) {
        Random random = new Random();

        int x = (int) random.nextInt(canvasWidth - 50) + 50;
        int y = (int) random.nextInt(canvasHeight - 50) + 50;
        if (color.equals(RED)) {
            return new Square(x, y, Color.RED);
        }
        int randomColor = random.nextInt(colors.length);
        return new Square(x, y, colors[randomColor]);
    }

    public Square getRedRect() {
        for (Square square : squares) {
            if (square.color == Color.RED) {
                return square;
            }
        }
        return null;
    }

    public void createAll() {
        squares.add(create("RED"));
        for (int i = 0; i < 25; i++) {
            squares.add(create("ANOTHER"));

        }
    }
}