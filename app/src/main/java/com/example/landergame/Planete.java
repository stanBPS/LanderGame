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
import android.provider.SyncStateContract;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.constraintlayout.solver.state.State;

public class Planete  {
    Bitmap planete;
    int left;
    int top;
    Paint paint;
    RectF planeteRect;
    int screenX;
    int screenY;
    int x =0;
    int y;


    public Planete(Resources res, int left, int top, DisplayMetrics metrics,int id, int sizeW, int sizeH){
        this.left = left;
        this.top = top;
        this.screenX = screenX;
        this.screenY = screenY;

        float scaleWidth = metrics.scaledDensity;
        float scaleHeight = metrics.scaledDensity;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        planete = BitmapFactory.decodeResource(res, id);
        planete= Bitmap.createScaledBitmap(planete, 86,86, false);
        planete = Bitmap.createBitmap(planete,0,0,planete.getWidth(), planete.getHeight(), matrix, true);
        paint = new Paint();
        planeteRect = new RectF(left,top,left+planete.getWidth(), top+planete.getHeight());
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(planete, left, top, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1); // set stroke width
        paint.setColor(Color.parseColor("#008000")); // set stroke color
        //canvas.drawRect(planeteRect, paint);
    }

    public RectF getShape(){
        //return new RectF(left,top,left+planete.getWidth(), top+planete.getHeight());
        return planeteRect;
    }

}
