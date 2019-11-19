package com.example.pong4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Paddle {

    private float screenWidth;
    private float screenHeight;
    private float x;
    private float y;
    private float width;
    private float height;
    private int score;
    private boolean isAI;
    private float dy;

    public Paddle(double screenWidth, double screenHeight, double width, double height, double x, double y, String type) {
        this.screenWidth = (float) screenWidth;
        this.screenHeight = (float) screenHeight;
        this.x = (float) x;
        this.y = (float) y;
        this.width = (float) width;
        this.height = (float) height;
        this.score = 0;
        this.isAI = (type.equals("ai")) ? true : false;
        this.dy = 0;
        System.out.println(this.screenHeight);
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        this.score++;
        System.out.println("Score incremented!");
    }

    public void update(float dt) {
        //System.out.println(this.y);
        this.y += this.dy * dt;
        if (this.y < 0)
            this.y = 0;
        if (this.y  + this.height >= this.screenHeight)
            this.y = this.screenHeight - this.height;
    }

    public void setDirection(String direction) {
        if (direction.equals("up"))
            this.dy = -5;
        else if (direction.equals("down"))
            this.dy = 5;
        else
            this.dy = 0;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
    }
}
