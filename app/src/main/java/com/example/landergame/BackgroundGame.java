package com.example.landergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BackgroundGame {

    int x=0, y;
    Bitmap background;


    public BackgroundGame( Resources res, int screenX, int screenY){
       background = BitmapFactory.decodeResource(res, R.drawable.pexels_francesco_ungar);
       background = Bitmap.createScaledBitmap(background, screenX,screenY , false);
    }

    public void draw(Canvas canvas) {
        Paint paint= new Paint();
        canvas.drawBitmap(background,x,y,paint);
    }
}
