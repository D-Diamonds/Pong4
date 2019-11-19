package com.example.pong4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

public class PongState {

    private int screenWidth;
    private int screenHeight;
    private View view;
    private Context context;

    private Ball ball;
    private Paddle player1;
    private Paddle player2;


    public PongState(View view, Context context) {
        this.view = view;
        this.context = context;
        this.screenWidth = view.findViewById(R.id.pongView).getWidth();
        this.screenHeight = view.findViewById(R.id.pongView).getHeight();
        System.out.println("DIMENSIONS: " + this.screenWidth + " * " + this.screenHeight);
        reset();

    }

    public void reset() {
        this.ball = new Ball(this.screenWidth, this.screenHeight);
        double width = this.screenWidth * .01;
        double height = this.screenHeight * .3;

        this.player1 = new Paddle(this.screenWidth, this.screenHeight, width, height, this.screenWidth * .1 - width, this.screenHeight * .5 - height / 2, "human");
        this.player2 = new Paddle(this.screenWidth, this.screenHeight, width, height, this.screenWidth * .9 - width, this.screenHeight * .5 - height / 2, "ai");
    }

    public void start() {
        this.ball.startMoving();
    }


    public void update(float dt) {
        this.ball.update(dt);
        this.player1.update(dt);
        this.player2.update(dt);

        if (this.ball.collides(this.player1)) {
            this.ball.setDx(-this.ball.getDx() * 1.02f);
            this.ball.setX(this.ball.getX() + this.ball.getRadius());
        }

        if (this.ball.collides(this.player2)) {
            this.ball.setDx(-this.ball.getDx() * 1.02f);
            this.ball.setX(this.ball.getX() - this.ball.getRadius());
        }

        if (this.ball.getX() < -this.ball.getRadius()) {
            this.player2.incrementScore();
            this.ball.recenter();
            updateScore(this.view, 2, this.player2.getScore());
        }
        if (this.ball.getX() > this.screenWidth + this.ball.getRadius()) {
            this.player1.incrementScore();
            this.ball.recenter();
            updateScore(this.view, 1, this.player1.getScore());
        }

    }

    public void updateScore(View view, int player, int score) {
        final int playerVal = player;
        final int scoreVal = score;
        System.out.println("View: " + view);
        System.out.println("Player: " + player);
        System.out.println("Score: " + score);

        ((Activity) this.context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (playerVal == 1)
                    ((TextView) ((Activity) context).findViewById(R.id.player1Score)).setText(Integer.toString(scoreVal));
                else if (playerVal == 2)
                    ((TextView) ((Activity) context).findViewById(R.id.player2Score)).setText(Integer.toString(scoreVal));
            }
        });
    }

    public void setDirection(String direction) {
        this.player1.setDirection(direction);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.WHITE);
        this.ball.draw(canvas, paint);
        this.player1.draw(canvas, paint);
        this.player2.draw(canvas, paint);
    }
}
