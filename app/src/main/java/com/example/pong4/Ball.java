package com.example.pong4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    private float screenWidth;
    private float screenHeight;
    private float radius;
    private float x;
    private float y;
    private float dx;
    private float dy;

    public Ball(float width, float height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.radius = (float) (this.screenHeight * .03);
        recenter();
        
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDx() {
        return this.dx;
    }

    public float getRadius() {
        return this.radius;
    }

    public void recenter() {
        this.x = (float) (this.screenWidth * .5 - this.radius);
        this.y = (float) (this.screenHeight * .5 - this.radius);
        this.dx = 0;
        this.dy = 0;
    }

    public boolean collides(Paddle paddle) {
        if (this.x - this.radius > paddle.getX() + paddle.getWidth() || paddle.getX() > this.x + this.radius)
            return false;
        if (this.y > paddle.getY() + paddle.getHeight() || paddle.getY() > this.y + this.radius)
            return false;
        return true;
    }

    public void startMoving() {
        if (this.dx == 0 && this.dy == 0) {
            int maxDx = 10;
            int minDx = -10;
            this.dx = (int) (Math.random() * (maxDx - minDx + 1)) + minDx;

            int maxDy = 5;
            int minDy = -5;
            this.dy = (int) (Math.random() * (maxDy - minDy + 1)) + minDy;
        }
    }


    public void update(float dt) {
        this.x += this.dx * dt;
        this.y += this.dy * dt;
        if (this.y - this.radius < 0) {
            this.y = this.radius;
            this.dy = -this.dy;
        }
        if (this.y + this.radius >= this.screenHeight) {
            this.y = this.screenHeight - this.radius;
            this.dy = -this.dy;
        }
        //System.out.println("Current x: " + this.x);
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
}
