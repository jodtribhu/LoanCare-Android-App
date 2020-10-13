package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=1100;
    MotionLayout motionLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        motionLayout= findViewById(R.id.motion_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                motionLayout.transitionToEnd();
                motionLayout.transitionToStart();
            }
        }, 100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreenActivity.this,
                        emi_calculator.class);
                startActivity(i);
                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}