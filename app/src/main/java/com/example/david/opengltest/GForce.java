package com.example.david.opengltest;

/**
 * Created by Zuqi Li on 2014-11-22.
 */
public class GForce {

    public GForce() {
    }

    public static void activateGForce(Ball M1, Ball M2) {
        final int gConstant = 10;
        double distance;
        double force;
        float x1 = M1.getX();
        float y1 = M1.getY();
        float x2 = M2.getX();
        float y2 = M2.getY();
        distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        force = (gConstant*M1.getMass()*M2.getMass())/(distance*distance);
        double A1 = force/M1.getMass();
        double A2 = force/M2.getMass();

        float xlength = x2-x1;
        float ylength = y2-y1;

        if(xlength > 0) {
            M1.setXAccel(A1/Math.sqrt(1+ylength*ylength/xlength/xlength));
            M2.setXAccel(-A2/Math.sqrt(1+ylength*ylength/xlength/xlength));
        } else if(xlength < 0) {
            M1.setXAccel(-A1/Math.sqrt(1+ylength*ylength/xlength/xlength));
            M2.setXAccel(A2/Math.sqrt(1+ylength*ylength/xlength/xlength));
        } else {
            M1.setXAccel(0);
            M2.setXAccel(0);
        }

        if(ylength > 0) {
            M1.setYAccel(A1/Math.sqrt(1+xlength*xlength/ylength/ylength));
            M2.setYAccel(-A2/Math.sqrt(1+xlength*xlength/ylength/ylength));
        } else if(ylength < 0) {
            M1.setYAccel(-A1/Math.sqrt(1+xlength*xlength/ylength/ylength));
            M2.setYAccel(A2/Math.sqrt(1+xlength*xlength/ylength/ylength));
        } else {
            M1.setYAccel(0);
            M2.setYAccel(0);
        }

    }
}