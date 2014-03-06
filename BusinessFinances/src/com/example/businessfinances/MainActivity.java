package com.example.businessfinances;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity implements GestureDetector.OnGestureListener {

    protected static dbAdapter db;
    private int currentTab;
    private TabHost tabHost;
    private GestureDetectorCompat mDetector;
    
    private final int NUM_TABS = 3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new dbAdapter(this);
        db.open();
        
        mDetector = new GestureDetectorCompat(this, this);

//        db.insertEntry(new Entry("test", 10));

        Resources ressources = getResources(); 
        tabHost = getTabHost(); 

        // Android tab
        Intent intentAlphabetical = new Intent().setClass(this, AlphabeticalActivity.class);
        TabSpec tabSpecAlphabetical = tabHost
                .newTabSpec("Alphabetical")
                .setIndicator("alphabetical")
                .setContent(intentAlphabetical);

        // Apple tab
        Intent intentAcending = new Intent().setClass(this, AcendingActivity.class);
        TabSpec tabSpecAcending = tabHost
                .newTabSpec("Acending")
                .setIndicator("acending")

                .setContent(intentAcending);

        // Windows tab
        Intent intentDecending = new Intent().setClass(this, DecendingActivity.class);
        TabSpec tabSpecDecending = tabHost
                .newTabSpec("Decending")
                .setIndicator("decending")

                .setContent(intentDecending);


        // add all tabs 
        tabHost.addTab(tabSpecAlphabetical);
        tabHost.addTab(tabSpecAcending);
        tabHost.addTab(tabSpecDecending);



        //set Windows tab as default (zero based)

        this.currentTab = 0;

        tabHost.setCurrentTab(currentTab);

    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
    
    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
    
    private void switchTabRight() {
        this.currentTab++;
        if (this.currentTab == NUM_TABS) {
            this.currentTab = 0;
        }
        tabHost.setCurrentTab(this.currentTab);
    }
    
    private void switchTabLeft() {
        this.currentTab--;
        if (this.currentTab < 0) {
            this.currentTab = NUM_TABS - 1;
        }
        tabHost.setCurrentTab(this.currentTab);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        if (velocityX > 0) {
            this.switchTabRight();
        } else {
            this.switchTabLeft();
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
}
