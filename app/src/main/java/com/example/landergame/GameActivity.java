package com.example.landergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private GamePanel gamePanel;
    private SensorManager sm = null;
    Sensor mMagneticField ;
    Sensor lightSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);


        requestWindowFeature( Window.FEATURE_NO_TITLE );

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        gamePanel = new GamePanel(this, point.x, point.y, metrics, v);
        setContentView(gamePanel);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(Lander.vitessedown != 0){
            int sensor = event.sensor.getType();

            synchronized (this) {
                if (sensor == Sensor.TYPE_ACCELEROMETER) {
                    float [] valuesAcc = event.values;
                    Lander.angle = valuesAcc[1]*10;
                    Lander.cos= Math.cos(Math.toRadians(Lander.angle));
                    Lander.sin=Math.sin(Math.toRadians(Lander.angle));
                    Log.d("TAG", "onSensorChanged: "+Lander.angle);
                }

            }
            if (sensor == Sensor.TYPE_LIGHT){
                LuxBar.value  = event.values[0];
                LuxBar.maxRange = lightSensor.getMaximumRange();
                Log.d("TAG", "lux: "+LuxBar.value/LuxBar.maxRange+" .");
            }
        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
         mMagneticField = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_LIGHT));
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}