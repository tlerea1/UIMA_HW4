package com.example.businessfinances;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    protected static dbAdapter db;
    private static int currentTab;
    protected static TabHost tabHost;
    private GestureDetectorCompat mDetector;
    
    private static final int NUM_TABS = 3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new dbAdapter(this);
        db.open();
        SwipeListener swipeList = new SwipeListener();
        mDetector = new GestureDetectorCompat(this, swipeList);

//        db.insertEntry(new Entry("test", 10));

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
    
    protected static void switchTabRight() {
        currentTab++;
        if (currentTab == NUM_TABS) {
            currentTab = 0;
        }
        tabHost.setCurrentTab(currentTab);
    }
    
    protected static void switchTabLeft() {
        currentTab--;
        if (currentTab < 0) {
            currentTab = NUM_TABS - 1;
        }
        tabHost.setCurrentTab(currentTab);
    }
}
