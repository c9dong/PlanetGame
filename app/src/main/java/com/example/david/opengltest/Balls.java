package com.example.david.opengltest;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by David on 2014-11-24.
 */
public class Balls {

    public static int sBall_id = 1;

    private Ball[] balls;
    int total_balls;
    int max_balls;

    public Balls(int n){
        balls = new Ball[n];
        total_balls = 0;
        max_balls = n;
    }

    public boolean addNewBalls(float x, float y, float r,float mass, float xSpeed, float ySpeed){
        if(total_balls < max_balls) {
            Ball b = new Ball(x, y, r, mass);
            b.setySpd(ySpeed);
            b.setxSpd(xSpeed);
            balls[total_balls] = b;
            total_balls ++;
            sBall_id++;
            return true;
        }else{
            return false;
        }
    }

    public int getTotal_balls(){
        return total_balls;
    }

    public Ball getBall(int index){
        if(index < max_balls){
            return balls[index];
        }
        return null;
    }

    public void drawAll(Canvas canvas){
        for(int i=0;i<total_balls;i++){
            if(balls[i]!=null) {
                balls[i].draw(canvas);
            }
        }
    }

    public void updateAll(Collision collision, Boundary boundary){
        for(int i=0;i<total_balls;i++){
            if(balls[i]!=null) {
                balls[i].update(collision, boundary, this);
            }
        }
    }

    public void ballTouchEventAll(MotionEvent e){
        for(int i=0;i<total_balls;i++){
            if(balls[i]!=null) {
                balls[i].ballTouchEvent(e);
            }
        }
    }
}
