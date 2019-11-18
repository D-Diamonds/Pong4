package com.example.pong4;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle {

    //private Canvas canvas;
    private Paint paint;
    private float screenWidth;
    private float screenHeight;
    private double x;
    private double y;

    public Paddle(Canvas canvas, Paint paint, float width, float height) {
        //this.canvas = canvas;
        this.paint = paint;
        this.screenWidth = width;
        this.screenHeight = height;

    }

    public void update(float fps) {

    }

    public void draw(Canvas canvas) {

    }
}
