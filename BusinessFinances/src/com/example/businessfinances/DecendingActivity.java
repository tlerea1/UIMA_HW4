package com.example.businessfinances;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DecendingActivity extends Activity {
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decending);   

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("C");
        strings.add("B");
        strings.add("A");
        ListView list = (ListView) findViewById(R.id.decending_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);
    }
}
