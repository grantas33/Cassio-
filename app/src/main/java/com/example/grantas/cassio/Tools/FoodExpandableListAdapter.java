package com.example.grantas.cassio.Tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Grantas on 2017-07-08.
 * Klase, isdestanti maistus lentele "Mano produktai"
 */

public class FoodExpandableListAdapter extends BaseExpandableListAdapter {

    Activity Context;

    protected List<Food> FoodList ;

    public FoodExpandableListAdapter(Activity context, List<Food> newList)
    {
        Context = context;
        FoodList = newList;
    }
    @Override
    public int getGroupCount() {
        return FoodList.size();
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

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

        ((TextView)view.findViewById(R.id.foodinfoname)).setText(item.Name);
        ((TextView)view.findViewById(R.id.foodinfocalories)).setText(String.format("{0} kal.", item.Calories));
        ((TextView)view.findViewById(R.id.foodinfograms)).setText(String.format("{0} g.", item.Grams));



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

        ((TextView)row.findViewById(R.id.foodinfocarbs)).setText(String.format("Carbohydrates: {0} g", item.Carbohydrates/100 * item.Grams));
        ((TextView)row.findViewById(R.id.foodinfoprotein)).setText(String.format("Protein: {0} g", item.Protein/100 * item.Grams));
        ((TextView)row.findViewById(R.id.foodinfofat)).setText(String.format("Fat: {0} g", item.Fat/100 * item.Grams));

        return row;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
