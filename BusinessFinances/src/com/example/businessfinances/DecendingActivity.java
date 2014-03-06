package com.example.businessfinances;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DecendingActivity extends Activity {
    
    private GestureDetectorCompat mDetector;

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decending);   

        SwipeListener swipeList = new SwipeListener();
        mDetector = new GestureDetectorCompat(this, swipeList);
        
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("C");
        strings.add("B");
        strings.add("A");
        ListView list = (ListView) findViewById(R.id.decending_list);
        ArrayAdapter<String> adapter = new ArrayAdapterWrapper<String>(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);
        
       list.setOnTouchListener(new View.OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchEvent(event);
            }
        });
    }
    
    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
}
