package com.example.pong4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class PongView extends SurfaceView implements Runnable, View.OnClickListener{

    private Thread thread;
    private SurfaceHolder drawingScreen;
    private Canvas canvas;
    private Paint paint;
    private float width;
    private float height;
    private float fps;
    volatile boolean playing;
    private boolean paused;
    private Ball ball;
    private Paddle player1;
    private Paddle player2;

    public PongView(Context context, AttributeSet attrs, int width, int height) {
        super(context, attrs);
        load(width, height);
        reset();
    }

    public PongView(Context context, AttributeSet attrs, int defStyle, int width, int height) {
        super(context, attrs, defStyle);
        load(width, height);
        reset();
    }
    public PongView(Context context, float width, float height) {
        super(context);
        load(width, height);
        reset();
    }

    public void load(float width, float height) {
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.drawingScreen = getHolder();
        this.paint = new Paint();
        this.ball = new Ball(this.canvas, this.paint, this.width, this.height);
        this.player1 = new Paddle(this.canvas, this.paint, this.width, this.height);
        this.player2 = new Paddle(this.canvas, this.paint, this.width, this.height);
    }

    public void reset() {
        this.ball.recenter();
    }

    public void start() {
        resume();
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / 60;
        while (this.playing) {
            startTime = System.nanoTime();

            if(!this.paused)
                update();
            draw();

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.thread.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == 60)        {
                this.fps = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                //System.out.println(this.fps);
            }
        }
    }

    public void draw() {
        //System.out.println("NOT DRAWING");
        if (this.drawingScreen.getSurface().isValid()) {
            this.canvas = this.drawingScreen.lockCanvas();
            //System.out.println("DRAWING");
            this.canvas.drawColor(Color.WHITE);
            this.ball.draw(this.canvas);
            this.player1.draw(this.canvas);
            this.player2.draw(this.canvas);
            this.drawingScreen.unlockCanvasAndPost(this.canvas);
        }
    }

    public void update() {
        //System.out.println("UPDATING!");
        this.ball.update(this.fps);
        this.player1.update(this.fps);
        this.player2.update(this.fps);
    }

    public void pause() {
        this.playing = false;
        try {
            this.thread.join();
        }
        catch (InterruptedException e) {
            Log.e("Error:", "thread");
        }
    }

    public void resume() {
        this.playing = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_MASK) {
            if (action == MotionEvent.ACTION_DOWN) {
                this.paused = false;
            }

            if (action == MotionEvent.ACTION_UP) {

            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == findViewById(R.id.resetBtn).getId())
            reset();
        if (view.getId() == findViewById(R.id.startBtn).getId())
            start();

    }
}
