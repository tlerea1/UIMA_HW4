package com.example.businessfinances;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class SwipeListener implements GestureDetector.OnGestureListener {

    private final float Y_SENSITIVITY = 70;
    
    public SwipeListener() {

    }
    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e0, MotionEvent e1, float velocityX,
            float velocityY) {
        double yDiff = e0.getY() - e1.getY();
        if (yDiff > Y_SENSITIVITY || yDiff < -Y_SENSITIVITY) {
            return true;
        }
        
//        if (e0.getX() < e1.getX()) {
//            MainActivity.switchTabRight();
//        } else {
//            MainActivity.switchTabLeft();
//        }
        
        if (velocityX > 0) {
            MainActivity.switchTabRight();
        } else {
            MainActivity.switchTabLeft();
        }
        return true;
        
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent e0, MotionEvent e1, float diffX,
            float diffY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

}
