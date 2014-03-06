package com.example.businessfinances;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class AlphabeticalActivity extends Activity {
    
    private ArrayList<Entry> entries;
    private ArrayAdapter<Entry> adapter;
    private Cursor cCursor;
    private Context context;
    private GestureDetectorCompat mDetector;

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabetical);
        
        SwipeListener swipeList = new SwipeListener();
        mDetector = new GestureDetectorCompat(this, swipeList);

        entries = new ArrayList<Entry>();
        context = getApplicationContext();

        
        ListView list = (ListView) findViewById(R.id.alphabetical_list);
        adapter = new ArrayAdapterWrapper<Entry>(this, android.R.layout.simple_list_item_1, entries);
        list.setAdapter(adapter);
        
        populateList();
        
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
    
    public void populateList()
    {
        cCursor = MainActivity.db.getAlphabetical();
        startManagingCursor(cCursor);
        updateArray();
    }
    
    public void updateArray()
    {
        cCursor.requery();  // UPDATE OR RELOAD CURSOR?
        entries.clear();
        if (cCursor.moveToFirst())
          do {
             Entry result = new Entry(cCursor.getString(1), cCursor.getDouble(2));
             entries.add(result);
        } while (cCursor.moveToNext());
        adapter.notifyDataSetChanged();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }
}
