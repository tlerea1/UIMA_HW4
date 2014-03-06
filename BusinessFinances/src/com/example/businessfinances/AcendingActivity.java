package com.example.businessfinances;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class AcendingActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acending);

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        ListView list = (ListView) findViewById(R.id.acending_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);
    }
}
