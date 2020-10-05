package com.army.adepter;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.army.R;
import com.army.fragment.HomeFragment;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    final static String[] myStringArray = new String[10];
    public static HashMap<String,String>  arrayList = new HashMap<String,String>();
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;


    //Dont't Change//////////------------------///////------///////////////////////
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }







    ///Here is getCHILDVIEW------------////////////////-------------//////////
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        //Don't change://///////////////////////////////-/////////////////////
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild =  convertView.findViewById(R.id.lblListItem);
        TextView price =  convertView.findViewById(R.id.price);



        price.setText("Rs."+(HomeFragment.PriceList.get(_listDataHeader.get(groupPosition)).get(childPosition)));


        txtListChild.setText(childText);





        //////////////------------------------------------------------------------/////


        return convertView;

    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

        ImageView checkk=convertView.findViewById(R.id.check);
        checkk.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(HomeFragment.c)
                    .setTitle("Are You Sure?")
                    .setPositiveButton("Yes",
                            (dialog, whichButton) -> {
                             dialog.dismiss();
                            }

                    )
                    .setNegativeButton("Cancel",
                            (dialog, whichButton) -> dialog.dismiss()
                    );
            b.show();


        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}


