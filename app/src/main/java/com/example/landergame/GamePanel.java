package com.example.landergame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class GamePanel  extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    Planete planete1;
    Planete planete2;
    Planete planete3;
    Planete planete4;
    Planete planete5;
    Planete planete6;
    Planete planete7;
    Planete planete8;
    private Asteroid asteroid1;
    private Asteroid asteroid2;
    private Asteroid asteroid3;
    private BackgroundGame backgroundGame;
    private PlatformPlanetes platform1;
    private PlatformPlanetes platform2;
    private PlatformPlanetes platform3;
    private PlatformPlanetes platform4;
    private PlatformPlanetes platform5;
    private PlatformPlanetes platform6;
    private PlatformPlanetes platform7;
    private PlatformPlanetes platform8;
    private int mViewWidth;
    private int mViewHeight;
    int screenX;
    int screenY;
    DisplayMetrics metrics;

    Bitmap pausePlay_bitmap;
    GButton pausePlay_button;
    GButton exit_button;
    Bitmap exit_bitmap;
    boolean r = true ;
    private Lander lander;
    Vibrator v;
    boolean canTouch = true;
    private int score;
    private Handler mHandler;

    boolean alreadyLand1;
    boolean alreadyLand2;
    boolean alreadyLand3;
    boolean alreadyLand4;
    boolean alreadyLand5;
    boolean alreadyLand6;
    boolean alreadyLand7;
    boolean alreadyLand8;
    boolean timePause = false;
    long totalSeconds = 30;
    long intervalSeconds = 1;
    long time;
    long timeLeft;
    long startTime;
    long displayTime;
    private Handler myHandler = new Handler();
    int seconds ;
    int minutes;
    int milliseconds;
    LuxBar luxBar;
    Typeface typeface ;
    int nbPlaneteLande;


    public GamePanel(Context context, int screenX, int screenY, DisplayMetrics metrics, Vibrator v){
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        this.metrics = metrics;
        this.v = v;

        alreadyLand1 = true;
        alreadyLand2 = true;
        alreadyLand3 = true;
        alreadyLand4 = true;
        alreadyLand5 = true;
        alreadyLand6 = true;
        alreadyLand7 = true;
        alreadyLand8 = true;

        getHolder().addCallback(this);
        startTime = SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerMethod, 0);

        thread = new GameThread(getHolder(), this);

        backgroundGame = new BackgroundGame(getResources() ,screenX,screenY);

        planete1 = new Planete(getResources(),  50,   128, metrics,R.drawable.neptune,screenX,screenY);
        planete2 = new Planete(getResources(),  296,  218, metrics,R.drawable.saturn,screenX,screenY);
        planete3 = new Planete(getResources(),  151,  457, metrics,R.drawable.uranus,screenX,screenY);
        planete4 = new Planete(getResources(),  620,  245, metrics,R.drawable.mars,screenX,screenY);
        planete5 = new Planete(getResources(),  430,  424, metrics,R.drawable.jupiter,screenX,screenY);
        planete6 = new Planete(getResources(),  790,  450, metrics,R.drawable.earth,screenX,screenY);
        planete7 = new Planete(getResources(),  930,  257, metrics,R.drawable.venus,screenX,screenY);
        planete8 = new Planete(getResources(),  1100, 137, metrics,R.drawable.mercury,screenX,screenY);

        platform1 = new PlatformPlanetes(getResources(),50,   128, planete1,screenX,screenY);
        platform2 = new PlatformPlanetes(getResources(),296,  217, planete2,screenX,screenY);
        platform3 = new PlatformPlanetes(getResources(),151,  456, planete3,screenX,screenY);
        platform4 = new PlatformPlanetes(getResources(),620,  245, planete4,screenX,screenY);
        platform5 = new PlatformPlanetes(getResources(),430,  424, planete5,screenX,screenY);
        platform6 = new PlatformPlanetes(getResources(),790,  450, planete6,screenX,screenY);
        platform7 = new PlatformPlanetes(getResources(),930,  256, planete7,screenX,screenY);
        platform8 = new PlatformPlanetes(getResources(),1100, 136, planete8,screenX,screenY);

        lander = new Lander(getResources());
        luxBar = new LuxBar(screenX,screenY,lander);
        typeface = getResources().getFont(R.font.atariclassic);

        nbPlaneteLande = 0;

        setFocusable(true);

    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            time = SystemClock.uptimeMillis() - startTime;
            displayTime = timeLeft + time;

             seconds = (int) (displayTime / 1000);
             minutes = seconds / 60;
            seconds = seconds % 60;
             milliseconds = (int) (displayTime % 1000);
            myHandler.postDelayed(this, 0);
        }

    };

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mViewWidth = getWidth();
        mViewHeight = getHeight();

        asteroid1 = new Asteroid(getResources(),50+(planete2.planete.getWidth()/2),  128+(planete2.planete.getHeight()/2), (Math.round((mViewWidth * 20)/ 100)) / 2,(float)(0.5));
        asteroid2 = new Asteroid(getResources(),1100+(planete3.planete.getWidth()/2),137+(planete3.planete.getHeight()/2), (Math.round((mViewWidth * 20)/ 100)) / 2,2);
        asteroid3 = new Asteroid(getResources(),620+(planete4.planete.getWidth()/2), 245+(planete4.planete.getHeight()/2), (Math.round((mViewWidth * 20)/ 100)) / 2,1);

        pausePlay_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_pausegame);
        pausePlay_bitmap = Bitmap.createScaledBitmap(pausePlay_bitmap, 40,40 , false);
        pausePlay_button = new GButton(40,40,pausePlay_bitmap);
        pausePlay_button.setPosition(screenX-125,8);

        exit_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cancel);
        exit_bitmap = Bitmap.createScaledBitmap(exit_bitmap, 40,40 , false);
        exit_button = new GButton(40,40,exit_bitmap);
        exit_button.setPosition(screenX-60,8);

        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if(thread != null){
            thread.setRunning(false);
            while (thread != null){
                try{
                    thread.join();
                    thread = null;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(){
        asteroid1.update();
        asteroid2.update();
        asteroid3.update();
        lander.update();
        if(canTouch){
           luxBar.update();
           if(lander.getAmplitude()>1000){
                lander.moveForward();
            }
        }

    }


    public void pause() {
        timeLeft += time;
        myHandler.removeCallbacks(updateTimerMethod);
        thread.onSuspend();
    }

    public void resume() {
        startTime = SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerMethod, 0);
        thread.onResume();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        if(canTouch){
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if (pausePlay_button.btn_rect.contains(x, y)) {
                        if (r == true){
                            r=false;
                            this.pause();
                        }else{
                            r=true;
                            this.resume();
                        }
                    }
                    if (exit_button.btn_rect.contains(x,y)){
                        thread.setRunning(false);
                        System.exit(0);
                    }
                    lander.setSpeed(0);
                    break;
                case MotionEvent.ACTION_MOVE:
                    lander.moveForward();
                    break;
                case MotionEvent.ACTION_UP:
                    lander.setSpeed(0.25);
                    break;
            }
        }



        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        backgroundGame.draw(canvas);
        planete1.draw(canvas);
        planete2.draw(canvas);
        planete3.draw(canvas);
        planete4.draw(canvas);
        planete5.draw(canvas);
        planete6.draw(canvas);
        planete7.draw(canvas);
        planete8.draw(canvas);
        asteroid1.draw(canvas);
        asteroid2.draw(canvas);
        asteroid3.draw(canvas);
        platform1.draw(canvas);
        platform2.draw(canvas);
        platform3.draw(canvas);
        platform4.draw(canvas);
        platform5.draw(canvas);
        platform6.draw(canvas);
        platform7.draw(canvas);
        platform8.draw(canvas);
        pausePlay_button.draw(canvas);

        lander.draw(canvas);
        luxBar.draw(canvas);
        exit_button.draw(canvas);

        String m = ""+minutes;
        String s = "" +seconds;
        if(minutes <10){
            m = "0"+minutes;
        }
        if(seconds <10){
            s = "0"+seconds;
        }

        if (nbPlaneteLande == 8){
            alreadyLand1 = true;
            alreadyLand2 = true;
            alreadyLand3 = true;
            alreadyLand4 = true;
            alreadyLand5 = true;
            alreadyLand6 = true;
            alreadyLand7 = true;
            alreadyLand8 = true;
        }

        if (RectF.intersects(lander.getShape(),asteroid1.getShape())
                || RectF.intersects(lander.getShape(),asteroid2.getShape())
                || RectF.intersects(lander.getShape(),asteroid3.getShape())){
            canTouch= false;
            lander.setSpeed(5);
            if (v.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }
            thread.setRunning(false);
            Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameOverIntent.putExtra("Score", score);
            getContext().startActivity(gameOverIntent);

           // Log.d("Touché","lander touche asteroid");
        }
        if (lander.getShape().intersect(planete1.getShape())
                || lander.getShape().intersect(planete2.getShape())
                || lander.getShape().intersect(planete3.getShape())
                || lander.getShape().intersect(planete4.getShape())
                || lander.getShape().intersect(planete5.getShape())
                || lander.getShape().intersect(planete6.getShape())
                || lander.getShape().intersect(planete7.getShape())
                || lander.getShape().intersect(planete8.getShape())){
            canTouch= false;
            lander.setSpeed(5);
            if (v.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }
            thread.setRunning(false);
            Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameOverIntent.putExtra("Score", score);
            getContext().startActivity(gameOverIntent);
           // Log.d("Touché","lander touche une planete");
        }
        if (lander.getBottomLeft().intersect(platform1.getTopLeft())
                && lander.getBottomRight().intersect(platform1.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand1){
                nbPlaneteLande++;
                score+=20;
            }
            alreadyLand1 = false;

            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform2.getTopLeft())
                && lander.getBottomRight().intersect(platform2.getTopRight())){
            lander.setSpeed(0);
            if (alreadyLand2){
                score+=10;
            }
            alreadyLand2 = false;
            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform3.getTopLeft())
                && lander.getBottomRight().intersect(platform3.getTopRight()) ){

            lander.setSpeed(0);
            if (alreadyLand3){
                nbPlaneteLande++;
                score+=10;
            }
            alreadyLand3 = false;

            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform4.getTopLeft())
                && lander.getBottomRight().intersect(platform4.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand4){
                nbPlaneteLande++;
                score+=30;
            }
            alreadyLand4= false;
            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform5.getTopLeft())
                && lander.getBottomRight().intersect(platform5.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand5){
                score+=10;
            }
            alreadyLand5 = false;

            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform6.getTopLeft())
                && lander.getBottomRight().intersect(platform6.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand6){
                nbPlaneteLande++;
                score+=10;
            }
            alreadyLand6 = false;
            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform7.getTopLeft())
                && lander.getBottomRight().intersect(platform7.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand7){
                nbPlaneteLande++;
                score+=10;
            }
            alreadyLand7 = false;
            Log.d("Touché","lander touche une platforme1");
        }
        if (lander.getBottomLeft().intersect(platform8.getTopLeft())
                && lander.getBottomRight().intersect(platform8.getTopRight()) ){
            lander.setSpeed(0);
            if (alreadyLand8){
                nbPlaneteLande++;
                score+=50;
            }
            alreadyLand8 = false;
            Log.d("Touché","lander touche une platforme1");
        }

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(14);
        paint.setTypeface(typeface);
        canvas.drawText("SCORE : "+score,10,30,paint);

        canvas.drawText("Time : "+m+" : "+s ,10,60,paint);

       // canvas.drawText("x : "+lander.speed*lander.sin,10,90,paint);
       // canvas.drawText("y : "+lander.speed*lander.cos,10,120,paint);
        //canvas.drawText("y : "+lander.accZ,10,150,paint);

    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        lander.x=w/2;
        lander.y=h/12;
    }

}
