package com.example.landergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Asteroid implements GameObject {

    private float asteroidCenterX;
    private float asteroidCenterY;

    private float asteroidBorderCenterX;
    private float asteroidBorderCenterY;
    private int mBorderRadius;
    private Paint mVisiblePaint;
    private Paint asteroidCountPaint;
    private double angle = 230; //230
    private int asteroidCountRadius;
    private Bitmap asteroid1;
    private float speed;
    RectF asteroidRect;

    public Asteroid(Resources res, float centerX, float centerY, int radius, float speed) {
        this.speed = speed;
        asteroidCenterX = centerX;
        asteroidCenterY = centerY;
        mBorderRadius = radius;
        asteroid1 = BitmapFactory.decodeResource(res, R.drawable.rock);
        asteroid1 = Bitmap.createScaledBitmap(asteroid1, 30,30 , false);

        createVisiblePaint();
        updatePosition(angle);
        asteroidRect = new RectF(asteroidBorderCenterX-mBorderRadius+110,asteroidBorderCenterY-mBorderRadius+110,asteroidBorderCenterX+mBorderRadius-110, asteroidBorderCenterY+mBorderRadius-110);

    }

    private void updatePosition(double angle) {
        angle = Math.toRadians(angle);
        asteroidBorderCenterX = (float) (asteroidCenterX + Math.cos(angle) * mBorderRadius);
        asteroidBorderCenterY = (float) (asteroidCenterY + Math.sin(angle) * mBorderRadius);
        asteroidRect = new RectF(asteroidBorderCenterX-mBorderRadius+110,asteroidBorderCenterY-mBorderRadius+110,asteroidBorderCenterX+mBorderRadius-110, asteroidBorderCenterY+mBorderRadius-110);
    }

    private void createVisiblePaint() {
        asteroidCountPaint = new Paint();
        asteroidCountRadius = mBorderRadius / 6;
    }



    @Override
    public void draw(Canvas canvas) {
        //canvas.drawCircle(asteroidBorderCenterX, asteroidBorderCenterY, asteroidCountRadius, asteroidCountPaint);
        canvas.drawBitmap(asteroid1,asteroidBorderCenterX-15,asteroidBorderCenterY-15,asteroidCountPaint);
      asteroidCountPaint.setStyle(Paint.Style.STROKE);
        asteroidCountPaint.setStrokeWidth(1); // set stroke width
        asteroidCountPaint.setColor(Color.parseColor("#008000")); // set stroke color
        //canvas.drawRect(asteroidRect, asteroidCountPaint);
    }

    @Override
    public void update() {
        if (angle > 360) {
            angle = 0;
        }
            angle = angle + speed;
            updatePosition(angle);
    }



    RectF getShape(){
        return asteroidRect;
    }

}
