package com.army.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import com.army.adepter.ExpandableListAdapter;
import com.army.R;

import com.army.database.DatabaseHelper;
import com.army.utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.army.activity.HomeActivity.fragmentManager;
import static com.army.activity.HomeActivity.titleChange;
import static com.army.activity.HomeActivity.topLyt;


public class HomeFragment extends Fragment {




    Unbinder unbinder;


    @BindView(R.id.lvExp)
    ExpandableListView expListView;




    private Context mContext;


    SessionManager sessionManager;
    //User user;

    DatabaseHelper databaseHelper;

    int prGroup=-1;


    public static Context c;
    ArrayList<String> NAMES;


    ExpandableListAdapter listAdapter;
    public static List<String> CustomerList;
    public static HashMap<String, List<String>> ItemList;
    public static HashMap<String, List<String>> PriceList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(mContext);
        databaseHelper = new DatabaseHelper(getActivity());
        c=getActivity();


        FloatingActionButton fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            Fragment fragment=new ItemsFragment();
            titleChange("Menu");
            topLyt.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("adds").commit();
        });


        prepareListData();
        return view;
    }


    private void prepareListData() {

        //expListView.setChildDivider(getResources().getDrawable(R.color.colorWhite));

        //Un comment below line to enable only one group expand at a time
//        expListView.setOnGroupExpandListener(i -> {
//            if ((prGroup != -1) && (i != prGroup)) {
//                expListView.collapseGroup(prGroup); }
//            prGroup = i;
//        });


        CustomerList = new ArrayList<String>();
        ItemList = new HashMap<String, List<String>>();
        PriceList = new HashMap<String, List<String>>();

        //Preparing Header Data////////////////////////////////////
        CustomerList.add("Customer1");
        CustomerList.add("Customer 2");
        CustomerList.add("Cstmr 3");


        //Preparing Child Strings:///////////////////////////////
        List<String> pg = new ArrayList<String>();
        List<String> pr = new ArrayList<String>();

        List<String> nur = new ArrayList<String>();
        List<String> prep = new ArrayList<String>();

        ////---------------------------------/////////////






        pr.add("160");
        pr.add("200");
        pg.add("Item1");
        pg.add("Item 2");
        PriceList.put(CustomerList.get(0),pr);
        ItemList.put(CustomerList.get(0),pg );

        nur.add("Omlette");
        nur.add("Naan");

        ItemList.put(CustomerList.get(1),nur );
        PriceList.put(CustomerList.get(1),pr);


        prep.add("Alu");
        prep.add("Karahi");

        ItemList.put(CustomerList.get(2),prep );
        PriceList.put(CustomerList.get(2),pr);


        listAdapter = new ExpandableListAdapter(getContext(), CustomerList, ItemList);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        ///--------------------------------------------------------////
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }







    @Override
    public void onResume() {
        super.onResume();



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
