package com.example.landergame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import  android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private final int TIME_LOADING = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(800);
        fadeOut.setDuration(2800);
        ImageView logo = (ImageView) findViewById(R.id.logo);

        logo.setAnimation(fadeOut);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainPage =  new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainPage);
                finish();
            }
        }, TIME_LOADING);

    }
}