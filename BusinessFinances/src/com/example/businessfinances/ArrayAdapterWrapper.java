package com.example.businessfinances;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ArrayAdapterWrapper extends ArrayAdapter<Entry>{

    private Context context;
    private List<Entry> items;
    
    public ArrayAdapterWrapper(Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position) && false;
        
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        View v = convertView;
        if (v == null) 
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_layout, null);  
        }
        Entry item = items.get(position);
        TextView name = (TextView) v.findViewById(R.id.item_name);
        TextView price = (TextView) v.findViewById(R.id.item_price);


        name.setText(item.getName());
        price.setText("$" + item.getPrice());

        return v;
    }
}
