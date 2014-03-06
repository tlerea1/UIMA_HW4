package com.example.businessfinances;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class AlphabeticalActivity extends Activity {
    
    private ArrayList<Entry> entries;
    private ArrayAdapter<Entry> adapter;
    private Cursor cCursor;
    private Context context;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabetical);
        

        entries = new ArrayList<Entry>();
        context = getApplicationContext();

        
        ListView list = (ListView) findViewById(R.id.alphabetical_list);
        adapter = new ArrayAdapter<Entry>(this, android.R.layout.simple_list_item_1, entries);
        list.setAdapter(adapter);
        
        populateList();
        
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
}
