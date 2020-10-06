package com.army.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.army.R;

import java.util.ArrayList;

public class youradapter extends BaseAdapter {

    Context context;

    ArrayList<String> NAMESs;

    private static LayoutInflater inflater = null;

    public youradapter(Context context, ArrayList<String> NAMES) {
        // TODO Auto-generated constructor stub
        this.context = context;

        this.NAMESs=NAMES;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return NAMESs.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return NAMESs.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row_layout, null);

        TextView hdr = (TextView) vi.findViewById(R.id.headerr);

        //hdr.setText(data2[position]);


        TextView text = (TextView) vi.findViewById(R.id.text);

        text.setText(NAMESs.get(position));
        return vi;
    }
}