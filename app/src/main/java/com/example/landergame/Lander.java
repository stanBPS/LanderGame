package com.example.landergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.MediaRecorder;

public class Lander implements  GameObject{

    float x,y;
    static float angle;
    static double speed = 2;
    static  double vitessedown=0.25;
    static double cos;
    static double sin;
    //static double accX;
    //static double accY;
    //static double accZ;

    Bitmap lander;
    RectF landerRect;
    RectF bottomLeft;
    RectF bottomRight;
    static MediaRecorder mRecorder;

    public Lander(Resources res){
        lander= BitmapFactory.decodeResource(res,R.drawable.lander1);
        lander=Bitmap.createScaledBitmap(lander,30,30,false);
        landerRect = new RectF(this.x,this.y,this.x+30, this.y+30);
        bottomLeft = new RectF(x,y+25,x+5,y+32);
       bottomRight = new RectF(x+25,y+32,x+30,y+25);

    }
    Bitmap getLander(){
        return rotateBitmap(lander,angle);
    }
    public Bitmap rotateBitmap(Bitmap original, float degrees) {
        int width = original.getWidth();
        int height = original.getHeight();

        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);

       // matrix.setRotate(degrees, landerRect.centerX(),landerRect.centerY());
        //matrix.mapRect(landerRect);

        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0,0, width, height, matrix, true);
        return rotatedBitmap;
    }

    public double getAmplitude() {
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude());
        else
            return 0;
    }

    public void moveForward()
    {
        x += speed * sin;
        y += -speed * cos;
        landerRect = new RectF(this.x,this.y,this.x+30, this.y+30);
        bottomLeft = new RectF(x,y+25,x+5,y+32);
        bottomRight = new RectF(x+25,y+32,x+30,y+25);
    }
    public void down(){
        y+=vitessedown;
        landerRect = new RectF(this.x,this.y,this.x+30, this.y+30);
        bottomLeft = new RectF(x,y+25,x+5,y+32);
        bottomRight = new RectF(x+25,y+32,x+30,y+25);
    }


    public void setSpeed(double s){
        vitessedown=s;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawBitmap(this.getLander(),this.x,this.y,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1); // set stroke width
        paint.setColor(Color.parseColor("#008000")); // set stroke color
        canvas.save();
        canvas.rotate(angle, landerRect.centerX(),landerRect.centerY());
        //canvas.drawRect(landerRect, paint);
        paint.setColor(Color.RED);
       // canvas.drawRect(bottomLeft,paint);
        canvas.rotate(angle, bottomLeft.centerX(),bottomLeft.centerY());
        //canvas.drawRect(bottomRight,paint);
        canvas.rotate(angle, bottomRight.centerX(),bottomRight.centerY());

        canvas.restore();
    }

    @Override
    public void update() {
        this.down();
    }

    public double getSpeed(){
        return speed;
    }

    RectF getShape(){
        return landerRect;
    }

    RectF getBottomRight(){
        return bottomRight;
    }
    RectF getBottomLeft(){
        return bottomLeft;
    }
}
