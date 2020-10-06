package com.army.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.army.R;
import com.army.activity.AddItemActivity;
import com.army.activity.HomeActivity;
import com.army.activity.LoginActivity;
import com.army.adepter.ItemsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    ListView pglist;
    ArrayList<String> ItemNames;
    ArrayList<String> Prices;

    FloatingActionButton addItem;


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
        addItem=view.findViewById(R.id.itemAddFAB);
        ItemNames.add("Item 1");
        ItemNames.add("Itemmm 2");

        Prices.add("Rs. 150");
        Prices.add("Rs. 210");

        pglist.setAdapter(new ItemsAdapter(getContext(), ItemNames,Prices));


        addItem.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AddItemActivity.class));

        });


        return view;
    }


}
