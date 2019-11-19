package com.example.pong4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    private float width;
    private float height;
    private float radius;
    private float x;
    private float y;
    private float dx;
    private float dy;

    public Ball(float width, float height) {
        this.width = width;
        this.height = height;
        this.radius = (float) (this.height * .03);
        recenter();
        
    }

    public float getX() {
        return this.x;
    }

    public float getRadius() {
        return this.radius;
    }

    public void recenter() {
        this.x = (float) (this.width * .5 - this.radius);
        this.y = (float) (this.height * .5 - this.radius);
        this.dx = 0;
        this.dy = 0;
    }

    public void startMoving() {
        int maxDx = 25;
        int minDx = -25;
        this.dx = (int) (Math.random() * (maxDx - minDx + 1)) + minDx;

        int maxDy = 10;
        int minDy = -10;
        this.dy = (int) (Math.random() * (maxDy - minDy + 1)) + minDy;
    }


    public void update(float dt) {
        this.x += this.dx * dt;
        this.y += this.dy * dt;
        if (this.y - this.radius < 0) {
            this.y = this.radius;
            this.dy = -this.dy;
        }
        if (this.y > this.height - this.radius) {
            this.y = this.height - this.radius;
            this.dy = -this.dy;
        }
        //System.out.println("Current x: " + this.x);
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
}
