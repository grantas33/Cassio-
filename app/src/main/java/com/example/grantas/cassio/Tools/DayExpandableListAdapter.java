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
import java.util.List;

/**
 * Created by Lukas on 7/19/2017.
 */

public class DayExpandableListAdapter extends BaseExpandableListAdapter {
    Activity context;
    Toast toast;
    private List<DayItem> days;
    private DayAdapterLogic logic;
//    private List<DayItem> filteredDays;
    private List<String> groups;

    public DayExpandableListAdapter(Activity context, List<DayItem> days) {
        this.context = context;
        this.days = days;
        logic = new DayAdapterLogic();
        groups = new ArrayList<>();
        for (DayItem day: days
                ) {
            String dayDate = day.getYearMonth();
            if (!groups.contains(dayDate)) {
                groups.add(dayDate);
            }
        }
    }

    @Override
    public int getGroupCount() {

        if (days != null) return groups.size();
        else return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String group = groups.get(groupPosition);
        int childrenCount = 0;
        for (DayItem day: days
             ) {
            if (day.getYearMonth() == group) {
                childrenCount++;
            }
        }
        return childrenCount;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
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
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {

        final DayItem item = days.get(groupPosition);
        final String group = groups.get(groupPosition);
        View view = convertView;
        if (view == null)
        {
            view = context.getLayoutInflater().inflate(R.layout.month_info_header, null);
        }

        ImageView expandMonth = (ImageView)view.findViewById(R.id.expand_month);
        int expandResourceId = isExpanded ? R.drawable.green_arrow_up : R.drawable.green_arrow_down;
//                R.drawable.ic_expand_less_black_48dp :
//                R.drawable.ic_expand_more_black_48dp;
        expandMonth.setImageResource(expandResourceId);

        expandMonth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        });

        ((TextView) view.findViewById(R.id.date_info)).setText(group);
        String  calories = getGroupCalories(groupPosition) + context.getString(R.string.cals);
        ((TextView) view.findViewById(R.id.calories_info)).setText(calories);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.month_info_child, null);
        }

        List<DayItem> filteredDays = filterDays(groupPosition);

        TextView dateInfo = (TextView) convertView.findViewById(R.id.date_child_info);
        dateInfo.setText(filteredDays.get(childPosition).getYearMonth());

        TextView caloriesInfo = (TextView) convertView.findViewById(R.id.calories_child_info);
        caloriesInfo.setText(filteredDays.get(childPosition).getCalories() + context.getString(R.string.cals));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void UpdateAdapter(List<DayItem> list)
    {
        this.days = list;
        notifyDataSetChanged();
    }

    private String getGroupCalories(int groupPosition) {
        List<DayItem> days = filterDays(groupPosition);
        int calories = 0;

        for (DayItem day:
             days) {
            calories += day.Calories;
        }
        return String.valueOf(calories);
    }

    private List<DayItem> filterDays(int groupPosition) {
        String date = groups.get(groupPosition);

        List<DayItem> filteredDays = new ArrayList<>();

        for (DayItem day:
                days) {
            String yearMonth = day.getYearMonth();
            if (yearMonth.equals(date)) {
                filteredDays.add(day);
            }
        }
        return filteredDays;
    }
}
