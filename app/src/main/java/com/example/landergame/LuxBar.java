package com.example.landergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LuxBar implements   GameObject{

    int screenX;
    int screenY;
    static float value;
    static float maxRange;
    Lander lander;

    public LuxBar(int screenX, int screenY, Lander lander){
        this.screenX = screenX;
        this.screenY = screenY;
        this.lander = lander;
    }

    @Override
    public void draw(Canvas canvas) {

        Paint bPaint = new Paint();
        float luxPourcentage =  value/(maxRange/100);
        float bLeft = screenX/2-200;
        float bTop = screenX/2;
        float bRight = screenX/2+200;
        float bBottom = screenX/2+15;
        bPaint.setStyle(Paint.Style.STROKE);
        bPaint.setStrokeWidth(1); // set stroke width
        bPaint.setColor(Color.parseColor("#008000")); // set stroke color
        canvas.drawRect(bLeft,bTop,bRight,bBottom,bPaint);

        Paint lPaint = new Paint();
        lPaint.setColor(Color.WHITE);
        float lLeft = screenX/2-200;
        float lTop = screenX/2;
        float lRight = lLeft + 200*luxPourcentage;
        float lBottom = screenX/2+15;
        canvas.drawRect(lLeft,lTop,lRight,lBottom,lPaint);
        canvas.drawText(""+luxPourcentage,screenX/2,screenY/2+260,lPaint);

    }

    public float getLux(){
        return value/(maxRange/100);
    }

    @Override
    public void update() {

        if (getLux() == 0){

            lander.moveForward();
        }else{
            lander.down();
            lander.setSpeed(0.25);
        }

    }
}
