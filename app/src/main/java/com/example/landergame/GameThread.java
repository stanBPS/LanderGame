package com.example.landergame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private volatile boolean running;
    public static Canvas canvas;
    private boolean Paused = false;


    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }



    public void setRunning(boolean running){
        this.running = running;
    }


    void onSuspend() {
        Paused = true;
    }

    synchronized void onResume(){
        Paused = false;
        notify();
    }


    @Override
    public void run(){


        long startTime;
        long elapsedTime;

        startTime = System.currentTimeMillis();
        while(running){
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
                synchronized(this) {
                    while(Paused) {
                        wait();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(canvas != null) {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e) {e.printStackTrace();}
                }
            }
            elapsedTime = System.currentTimeMillis() - startTime;

        }

    }
}
