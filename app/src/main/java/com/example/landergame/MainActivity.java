package com.example.landergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView start;
    private  ImageView quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        this.start = (ImageView) findViewById(R.id.start);
        this.quitter = (ImageView) findViewById(R.id.quiiter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamePage = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(gamePage);
                finish();
            }
        });


        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }






    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Lander.mRecorder = new MediaRecorder();
                    Lander.mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    Lander.mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    Lander.mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    Lander.mRecorder.setOutputFile("/dev/null");
                    try {
                        Lander.mRecorder.prepare();
                        Lander.mRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

        }
    }
}