package com.example.businessfinances;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;


public class ArrayAdapterWrapper<T> extends ArrayAdapter<T>{

    public ArrayAdapterWrapper(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position) && false;
        
    }

}
