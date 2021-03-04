package com.example.landergame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private ImageView retry;
    private  ImageView backToMenu;

    private  TextView endScreenScore;
    private  int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature( Window.FEATURE_NO_TITLE );

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

         score = Integer.valueOf(getIntent().getExtras().get("Score").toString());





        setContentView(R.layout.activity_gameover);

        endScreenScore = (TextView) findViewById(R.id.endScreenScore);

        this.retry = (ImageView) findViewById(R.id.retry);

        this.backToMenu = (ImageView) findViewById(R.id.backToMenu);


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamePage = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(gamePage);
                finish();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuPage = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(menuPage);
                finish();
            }
        });

        endScreenScore.setText("Score : "+score);

    }
}
