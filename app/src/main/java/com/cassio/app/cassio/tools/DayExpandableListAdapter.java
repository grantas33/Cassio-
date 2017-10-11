package com.cassio.app.cassio.tools;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cassio.app.cassio.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
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
    private HorizontalBarChart stackedChart;

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
        String  calories = context.getString(R.string.average) + " " + getGroupCalories(groupPosition) + " " + context.getString(R.string.cals);
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
        stackedChart = (HorizontalBarChart) convertView.findViewById(R.id.stackedchart);
        setupChart(day);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setupChart(DayItem day)
    {
        stackedChart.getDescription().setEnabled(false);

        stackedChart.setClickable(false);
        stackedChart.setPinchZoom(false);
        stackedChart.getAxisLeft().setDrawLabels(false);
        stackedChart.getAxisRight().setDrawLabels(false);
        stackedChart.getXAxis().setDrawLabels(false);
        stackedChart.getLegend().setEnabled(false);
        stackedChart.getAxisLeft().setDrawGridLines(false);
        stackedChart.getXAxis().setEnabled(false);
        stackedChart.getAxisLeft().setDrawAxisLine(false);
        stackedChart.getXAxis().setDrawAxisLine(false);
        stackedChart.getAxisLeft().setEnabled(false);
        stackedChart.getAxisRight().setEnabled(false);
        stackedChart.setTouchEnabled(false);
        stackedChart.setDrawValueAboveBar(false);





        BarEntry entry = new BarEntry(1000f, new float[]{(float)day.getCarbohydratePercent(), (float)day.getProteinPercent(), (float)day.getFatPercent()});
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(entry);
        BarDataSet set1 = new BarDataSet(entries, null);
        set1.setDrawIcons(false);
        set1.setColors(Color.rgb(139, 204, 40), Color.rgb(40, 139, 204), Color.rgb(204, 40, 139));
        set1.setValueTextColor(Color.rgb(250, 250, 250));

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter(new DayItemValueFormatter());
        data.setValueTextSize(5f);

        stackedChart.setData(data);
       // stackedChart.setViewPortOffsets(0f, 0f, 0f, 0f);
        stackedChart.invalidate();

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
        return String.valueOf(calories/getChildrenCount(groupPosition));
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
