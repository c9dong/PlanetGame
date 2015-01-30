package com.example.david.opengltest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by David on 2014-11-21.
 */
public class DrawingView extends View {
    Balls balls;
    GForce gforce;
    Boundary boundary;
    Collision collision;
    int screen_height;
    int screen_width;
    public DrawingView(Context context) {
        super(context);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        screen_width = display.getWidth();  // deprecated
        screen_height = display.getHeight();

        balls = new Balls(100);
        balls.addNewBalls(50,50,15,50,0,0);
        balls.addNewBalls(250,500,50,2000,0,0);
        balls.addNewBalls(400,950,25,500,0,0);

        boundary = new Boundary(0,0,screen_width,screen_height);
    }
   @ Override
    public void onDraw(Canvas canvas){
       super.onDraw(canvas);
        balls.drawAll(canvas);

       update();
      // try{
       //    Thread.sleep(10);
       //}catch(InterruptedException e){}

       invalidate();
   }
    public void update(){
        balls.updateAll(collision,boundary);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        balls.ballTouchEventAll(e);
        return true;
    }
}
