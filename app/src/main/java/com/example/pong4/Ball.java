package com.example.pong4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    //private Canvas canvas;
    private Paint paint;
    private float width;
    private float height;
    private float radius;
    private float x;
    private float y;
    private float dx;
    private float dy;

    public Ball(Canvas canvas, Paint paint, float width, float height) {
        this.paint = paint;
        this.width = width;
        this.height = height;
        this.radius = (float) (this.height * .05);
        this.dx = 10;
        this.dy = 0;
        recenter();
        System.out.println("New Ball created");
        System.out.println("X:" + this.x);
        
    }

    public void recenter() {
        this.x = (float) (this.width * .5 - this.radius);
        this.y = (float) (this.height * .5 - this.radius);
        //System.out.println("Starting x: " + this.x);
        this.dx = 10;
        this.dy = 0;
    }


    public void update(float fps) {
        //System.out.println("Current FPS: " + fps);
        //System.out.println("Current x: " + this.x);
        this.x += this.dx / fps;
        this.y += this.dy / fps;
    }

    public void draw(Canvas canvas) {
        this.paint.setColor(Color.BLUE);
        canvas.drawCircle(this.x, this.y, this.radius, this.paint);
    }
}
