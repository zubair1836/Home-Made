package com.army.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.army.R;
import com.army.adepter.ItemsAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import static com.army.activity.HomeActivity.c;
import static com.army.activity.HomeActivity.homeActivity;

public class MealFragment extends Fragment {


    SwipeMenuListView listView;
    ArrayList<String> ItemNames;
    ArrayList<String> Prices;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals, container, false);

        listView=view.findViewById(R.id.listView);
        ItemNames = new ArrayList<>();
        Prices = new ArrayList<>();






        ItemNames.add("Meal 1");
        ItemNames.add("Meal 2");

        Prices.add("Rs. 150");
        Prices.add("Rs. 210");

        //listView.setAdapter(new AppAdapter(c, ItemNames,Prices));


        return view;
    }

//
//    public class AppAdapter extends BaseAdapter {
//
//        private  LayoutInflater inflater = null;
//        Context context;
//
//        ArrayList<String> ItemNames;
//        ArrayList<String> Prices;
//
//
//        public AppAdapter(Context context, ArrayList<String> itemNames, ArrayList<String> Prices) {
//            // TODO Auto-generated constructor stub
//            this.context = getContext();
//
//            this.ItemNames =itemNames;
//            this.Prices=Prices;
//
//            inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//
//        @Override
//        public int getViewTypeCount() {
//            // menu type count
//            return 2;
//        }
//
//        @Override
//        public int getCount() {
//            return ItemNames.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return ItemNames.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//
//            View vi = view;
//            if (vi == null)
//                vi = inflater.inflate(R.layout.row_layout, null);
//
//            TextView hdr = vi.findViewById(R.id.headerr);
//
//            hdr.setText(ItemNames.get(i));
//
//            TextView price =  vi.findViewById(R.id.text);
//
//            price.setText(Prices.get(i));
//
//
//
//
//
//
//
//
//            return null;
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            // current menu type
//            return position;
//        }
//
//    }


}
