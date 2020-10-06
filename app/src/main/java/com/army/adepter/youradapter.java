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

    ArrayList<String> ItemNames;
    ArrayList<String> Prices;


    private static LayoutInflater inflater = null;

    public youradapter(Context context, ArrayList<String> itemNames, ArrayList<String> Prices) {
        // TODO Auto-generated constructor stub
        this.context = context;

        this.ItemNames =itemNames;
        this.Prices=Prices;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ItemNames.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return ItemNames.get(position);
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

        hdr.setText(ItemNames.get(position));

        TextView price = (TextView) vi.findViewById(R.id.text);

        price.setText(Prices.get(position));
        return vi;
    }
}