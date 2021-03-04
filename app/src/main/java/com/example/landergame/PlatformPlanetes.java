package com.example.landergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public class PlatformPlanetes implements GameObject{

    Bitmap platform;
    Planete p;
    Paint paint;
    int screenX;
    int screenY;
    int left;
    int top;
    RectF platformRect;
    RectF topLeft;
    RectF topRight;

    public PlatformPlanetes(Resources res, int left, int top, Planete p, int screenX, int screenY){
        this.p = p;
        this.left = left;
        this.top = top;
        this.screenX = screenX;
        this.screenY = screenY;


        platform = BitmapFactory.decodeResource(res, R.drawable.step_platform1);
        platform = Bitmap.createScaledBitmap(platform, 30,30, false);


       // platformRect = new RectF(left+42,top,(left+42)+platform.getWidth(), (top-36)+platform.getHeight());
        platformRect = new RectF(left+42,top,(left+42)+platform.getWidth(), (top-36)+platform.getHeight());
        topLeft = new RectF(left+42,top-8,left+42+5,top);

        topRight =  new RectF(left+68,top-8,(left+42+30),top);


        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(platform, left+42, top-16, paint);
       /* paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1); // set stroke width
        paint.setColor(Color.parseColor("#008000")); // set stroke color
        canvas.drawRect(platformRect, paint);*/
        paint.setColor(Color.RED);
        //canvas.drawRect(topLeft,paint);
        //canvas.drawRect(topRight,paint);


    }

    @Override
    public void update() {
        // platformRect = new RectF(left+42,top,(left+42)+platform.getWidth(), (top-36)+platform.getHeight());
        platformRect = new RectF(left+42,top,(left+42)+platform.getWidth(), (top-36)+platform.getHeight());
         topLeft = new RectF(left+42,top-8,left+42+5,top);

        topRight =  new RectF(left+68,top-8,(left+42+30),top);

    }

    RectF getShape(){
        //return new Rect(left+42,top,(left+42)+platform.getWidth(), (top-36)+platform.getHeight());
        return  platformRect;
    }
    RectF getTopRight(){
        return topRight;
    }
    RectF getTopLeft(){
        return topLeft;
    }
}
