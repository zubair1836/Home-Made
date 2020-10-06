package com.army.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.army.R;
import com.army.adepter.youradapter;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    ListView pglist;
    ArrayList<String> ItemNames;
    ArrayList<String> Prices;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        ItemNames = new ArrayList<>();
        Prices = new ArrayList<>();

        pglist=view.findViewById(R.id.lvItems);
        ItemNames.add("Item 1");
        ItemNames.add("Itemmm 2");

        Prices.add("Rs. 150");
        Prices.add("Rs. 210");



        pglist.setAdapter(new youradapter(getContext(), ItemNames,Prices));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                getContext(),
//                android.R.layout.simple_list_item_1, NAMES);
//        pglist.setAdapter(arrayAdapter);



        return view;
    }


}
