package com.example.pong4;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {

    private PongView pongView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.pongView = findViewById(R.id.pongView);


        findViewById(R.id.resetBtn).setOnClickListener(this.pongView);
        findViewById(R.id.startBtn).setOnClickListener(this.pongView);

    }
}