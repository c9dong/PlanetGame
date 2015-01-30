package com.example.david.opengltest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by David on 2014-11-21.
 */
public class Ball {

    private int id;
    private boolean followMouse = false;
    private float x;
    private float y;
    private float preX;
    private float preY;
    private float dx;
    private float dy;
    private float r;
    private float deceleration;
    private double xAccel;
    private double yAccel;
    private float mass;

    private float xSpd;
    private float ySpd;

    private Paint paint;

    int color = Color.BLUE;
    RectF bounds;

    public Ball(float x, float y, float r,float mass){
        id = Balls.sBall_id;
        this.x = x;
        this.y = y;
        preX = 0;
        preY = 0;
        dx = 0;
        dy = 0;
        this.r = r;
        this.mass = mass;
        xSpd = 5;
        ySpd = 5;
        //deceleration = (float)0.1;
        deceleration = 0;
        paint = new Paint();
        paint.setColor(color);
        bounds = new RectF(x-r, y-r, x+r, y+r);
    }

    //Region getter and setters
    public float getMass() {
        return mass;
    }

    public void setXAccel(double xAccel) {
       this.xAccel = xAccel;
    }

    public void setYAccel(double yAccel) {
        this.yAccel = yAccel;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }
    public void setxSpd(float xSpd) {
        this.xSpd = xSpd;
    }

    public float getySpd() {
        return ySpd;
    }

    public void setySpd(float ySpd) {
        this.ySpd = ySpd;
    }

    public float getPreX() {
        return preX;
    }

    public void setPreX(float preX) {
        this.preX = preX;
    }

    public float getPreY() {
        return preY;
    }

    public void setPreY(float preY) {
        this.preY = preY;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getxSpd() {
        return xSpd;
    }

    public int getId(){
        return id;
    }
    //End region getter and setters

    public void draw(Canvas canvas){
        canvas.drawOval(bounds, paint);
    }

    public void update(Collision collision, Boundary boundary, Balls balls){
        calcGForce(balls);
        if(!followMouse) {
            xSpd += xAccel;
            ySpd += yAccel;
            x += xSpd;
            y += ySpd;
            calcAcceleration();
        }
        collision.ballBoundCollision(this, boundary);
        bounds = new RectF(x - r, y - r, x + r, y + r);
    }

    private void calcGForce(Balls balls){
        for(int i=0;i<balls.getTotal_balls();i++){
            if(this.id!=balls.getBall(i).getId()){
                GForce.activateGForce(this,balls.getBall(i));
            }
        }
    }

    private void calcAcceleration(){
        if (xSpd < 0) {
            xSpd += deceleration;
        } else if (xSpd > 0) {
            xSpd -= deceleration;
        }
        if (Math.abs(xSpd) < deceleration) {
            xSpd = 0;
        }
        if (ySpd < 0) {
            ySpd += deceleration;
        } else if (ySpd > 0) {
            ySpd -= deceleration;
        }
        if (Math.abs(ySpd) < deceleration) {
            ySpd = 0;
        }
    }


    public void stop(){
        xSpd = 0;
        ySpd = 0;
    }

    private void clickBall(float cx, float cy){
        if(Math.abs(x-cx) <= r && Math.abs(y-cy) <= r){
            stop();
            followMouse = true;
        }
    }

    private void loseFocusBall(){
        if(this.isFollowMouse()) {
            this.setxSpd(this.getDx() / 10);
            this.setySpd(this.getDy() / 10);
            this.stopFollowMouse();
        }
    }
    
    public void ballTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();
        if(this.isFollowMouse()){
            followMouse(x,y);
        }
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.clickBall(x,y);
                break;
            case MotionEvent.ACTION_UP:
                loseFocusBall();
                break;
            case MotionEvent.ACTION_MOVE:
                this.setDx((x-this.getPreX()));
                this.setDy((y-this.getPreY()));
                break;
        }
        this.setPreX(x);
        this.setPreY(y);
    }

    public boolean isFollowMouse(){
        return followMouse;
    }

    public void stopFollowMouse(){
        followMouse = false;
    }

    private void followMouse(float x, float y){
        this.x = x;
        this.y = y;
    }
}
