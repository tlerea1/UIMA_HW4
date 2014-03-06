package com.example.businessfinances;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity{

    protected static dbAdapter db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new dbAdapter(this);
        db.open();

//        db.insertEntry(new Entry("test", 10));

        Resources ressources = getResources(); 
        TabHost tabHost = getTabHost(); 

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

        int currentTab = 0;

        tabHost.setCurrentTab(currentTab);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
