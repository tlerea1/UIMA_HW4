package com.example.businessfinances;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class SwipeListener implements GestureDetector.OnGestureListener {

    private final float Y_SENSITIVITY = 2;
    
    public SwipeListener() {

    }
    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float velocityX,
            float velocityY) {
//        if (velocityY > Y_SENSITIVITY || velocityY < -Y_SENSITIVITY) {
//            return true;
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
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
            float arg3) {
        // TODO Auto-generated method stub
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
