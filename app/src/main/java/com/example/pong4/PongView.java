package com.example.pong4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PongView extends SurfaceView implements SurfaceHolder.Callback, View.OnClickListener {

    private PongThread thread;
    private Context context;

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        System.out.println(getWidth());
        this.post(new Runnable() {
            @Override
            public void run() {
                setThread(new PongThread(getHolder(), getContext(), new Handler(){
                    @Override
                    public void publish(LogRecord record) {

                    }

                    @Override
                    public void flush() {

                    }

                    @Override
                    public void close() throws SecurityException {

                    }
                }, getThis().getRootView()));
                getThread().start();
            }
        });
    }

    public PongView getThis() {
        return this;
    }

    public Thread getThread() {
        return this.thread;
    }

    public void setThread(PongThread thread) {
        this.thread = thread;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startBtn) {
            this.thread.getPongState().start();
        }
        if (v.getId() == R.id.resetBtn) {
            this.thread.getPongState().reset();
            ((Activity) this.context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView) ((Activity) context).findViewById(R.id.player1Score)).setText(Integer.toString(0));
                    ((TextView) ((Activity) context).findViewById(R.id.player2Score)).setText(Integer.toString(0));
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (event.getX() >= getWidth() * .5)
                this.thread.getPongState().setDirection("down");
            else if (event.getX() < getWidth() * .5)
                this.thread.getPongState().setDirection("up");

        }
        if (action == MotionEvent.ACTION_UP)
            this.thread.getPongState().setDirection("null");
        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.thread.stop();
    }
}
