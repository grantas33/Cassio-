package com.example.grantas.cassio.Tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Grantas on 2017-07-08.
 * Klase, isdestanti maistus lentele "Mano produktai"
 */

public class FoodExpandableListAdapter extends BaseExpandableListAdapter
{

    Activity Context;

    protected List<Food> FoodList ;


    public FoodExpandableListAdapter(Activity context, List<Food> newList)
    {
        Context = context;
        FoodList = newList;

    }
    @Override
    public int getGroupCount() {
        if(FoodList != null) return FoodList.size();
        else return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {

        Food item = FoodList.get(groupPosition);
        View view = convertView;
        if (view == null)
        {
            view = Context.getLayoutInflater().inflate(R.layout.food_info_header, null);
        }



        ImageView listHeaderPlusSign = (ImageView)view.findViewById(R.id.greenplus);
        listHeaderPlusSign.setImageResource(R.drawable.green_plus);

        ImageView listHeaderArrow = (ImageView)view.findViewById(R.id.greenarrow);
        int imageResourceId = isExpanded ? R.drawable.green_arrow_up : R.drawable.green_arrow_down;
        listHeaderArrow.setImageResource(imageResourceId);

        listHeaderArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);

            }
        });


        ((TextView)view.findViewById(R.id.foodinfoname)).setText(item.Name);
        ((TextView)view.findViewById(R.id.foodinfocalories)).setText(item.Calories + " kal.");
        ((TextView)view.findViewById(R.id.foodinfograms)).setText(item.Grams + " g.");



        return view;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Food item = FoodList.get(groupPosition);
        View row = convertView;
        if (row == null)
        {
            row = Context.getLayoutInflater().inflate(R.layout.food_info_child, null);
        }

        ((TextView)row.findViewById(R.id.foodinfocarbs)).setText(String.format(Locale.getDefault(), "%s: %.2f g.", Context.getString(R.string.carbohydrates), item.Carbohydrates/100 * item.Grams));
        ((TextView)row.findViewById(R.id.foodinfoprotein)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.protein), item.Protein/100 * item.Grams));
        ((TextView)row.findViewById(R.id.foodinfofat)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.fat), item.Fat/100 * item.Grams));

        return row;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void UpdateAdapter(List<Food> list)
    {
        this.FoodList = list;
        notifyDataSetChanged();
    }


}


