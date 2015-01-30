package com.example.david.opengltest;

/**
 * Created by David on 2014-11-21.
 */
public class Collision {
    private static volatile Collision instance = null;
    private Collision(){

    }
    public static Collision getInstance(){
        if(instance == null){
            synchronized (Collision.class){
                if(instance == null){
                    instance = new Collision();
                }
            }
        }
        return instance;
    }

    public static void ballBoundCollision(Ball ball, Boundary boundary){
        float x = ball.getX();
        float y = ball.getY();
        float r = ball.getR();
        int left = boundary.getLeft();
        int top = boundary.getTop();
        int right = boundary.getRight();
        int bottom = boundary.getBottom();
        if(x-r < left){
            ball.setxSpd(ball.getxSpd()*-1);
            ball.setX(left+r);
        }
        if(x+r > right){
            ball.setxSpd(ball.getxSpd()*-1);
            ball.setX(right-r);
        }
        if(y-r < top){
            ball.setySpd(ball.getySpd()*-1);
            ball.setY(top+r);
        }
        if(y+r > bottom){
            ball.setySpd(ball.getySpd()*-1);
            ball.setY(bottom-r);
        }
    }
}
