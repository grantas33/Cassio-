package com.example.grantas.cassio.Tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.DayAdapterLogic;
import com.example.grantas.cassio.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 7/19/2017.
 */

public class DayExpandableListAdapter extends BaseExpandableListAdapter {
    Activity context;
    Toast toast;
    private HashMap< String, List<DayItem>> expandableListDetail;
    private List<String> expandableMonthTitle;

    public DayExpandableListAdapter(Activity context, List<String> expandableMonthTitle,
                                    HashMap<String, List<DayItem>> expandableListDetail) {
        this.context = context;
        this.expandableMonthTitle = expandableMonthTitle;
        this.expandableListDetail = expandableListDetail;

    }

    @Override
    public int getGroupCount() {

        if (expandableListDetail != null) return expandableMonthTitle.size();
        else return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableListDetail.get(expandableMonthTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return expandableMonthTitle.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandableListDetail.get(expandableMonthTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {

        final String monthTitle = (String) getGroup(groupPosition);

        if (convertView == null)
        {
            convertView = context.getLayoutInflater().inflate(R.layout.month_info_header, null);
        }

//        ImageView expandMonth = (ImageView)view.findViewById(R.id.expand_month);
//        int expandResourceId = isExpanded ?
//                R.drawable.ic_expand_less_black_48dp :
//                R.drawable.ic_expand_more_black_48dp;
//        expandMonth.setImageResource(expandResourceId);
//
//        expandMonth.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
//                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
//            }
//        });

        ((TextView) convertView.findViewById(R.id.date_info)).setText(monthTitle);
        String  calories = getGroupCalories(groupPosition) + context.getString(R.string.cals);
        ((TextView) convertView.findViewById(R.id.calories_info)).setText(calories);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.month_info_child, null);
        }

//        List<DayItem> filteredDays = filterDays(groupPosition);
        final DayItem day = (DayItem) getChild(groupPosition, childPosition);
        TextView dateInfo = (TextView) convertView.findViewById(R.id.date_child_info);
        dateInfo.setText(day.getFullDate());

        TextView caloriesInfo = (TextView) convertView.findViewById(R.id.calories_child_info);
        caloriesInfo.setText(day.getCalories() + context.getString(R.string.cals));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private String getGroupCalories(int groupPosition) {
        String group = (String) getGroup(groupPosition);
        List<DayItem> days = expandableListDetail.get(group);
//                expandableListDetail.get(group);
//                (List<DayItem>) getGroup(groupPosition);
        int calories = 0;

        for (DayItem day:
             days) {
            calories += day.Calories;
        }
        return String.valueOf(calories);
    }

//    private List<DayItem> filterDays(int groupPosition) {
//        String date = groups.get(groupPosition);
//
//        List<DayItem> filteredDays = new ArrayList<>();
//
//        for (DayItem day:
//                days) {
//            String yearMonth = day.getYearMonth();
//            if (yearMonth.equals(date)) {
//                filteredDays.add(day);
//            }
//        }
//        return filteredDays;
//    }
}
