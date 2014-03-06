package com.example.businessfinances;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
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
                .setIndicator("Alphabetical")
                .setContent(intentAlphabetical);

        // Apple tab
        Intent intentAcending = new Intent().setClass(this, AcendingActivity.class);
        TabSpec tabSpecAcending = tabHost
                .newTabSpec("Acending")
                .setIndicator("Acending")

                .setContent(intentAcending);

        // Windows tab
        Intent intentDecending = new Intent().setClass(this, DecendingActivity.class);
        TabSpec tabSpecDecending = tabHost
                .newTabSpec("Decending")
                .setIndicator("Decending")

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
    
    public void showAdd(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_layout, null);
        builder.setView(view);
        builder.setTitle("Add Entry");
        builder
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText nameBox = (EditText) view.findViewById(R.id.addName);
                        EditText valueBox = (EditText) view.findViewById(R.id.addCost);
                        String name = nameBox.getText().toString();

                        Double value = Double.parseDouble(valueBox.getText().toString());
                        db.insertEntry(new Entry(name, value));
                        String tab = getTabHost().getCurrentTabTag();
                        Activity tabActivity1 = getLocalActivityManager().getActivity(tab);
                        if(tabActivity1 != null){
                            if(tabActivity1 instanceof AcendingActivity){
                                AcendingActivity acendingActivity = (AcendingActivity) getLocalActivityManager().getActivity(tab);
                                acendingActivity.onResume();
                            }
                            if(tabActivity1 instanceof DecendingActivity){
                                DecendingActivity decendingActivity = (DecendingActivity) getLocalActivityManager().getActivity(tab);
                                decendingActivity.onResume();
                            }
                            if(tabActivity1 instanceof AlphabeticalActivity){
                                AlphabeticalActivity alphabeticalActivity = (AlphabeticalActivity) getLocalActivityManager().getActivity(tab);
                                alphabeticalActivity.onResume();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
