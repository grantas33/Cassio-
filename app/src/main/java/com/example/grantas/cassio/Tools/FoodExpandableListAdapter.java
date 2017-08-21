package com.example.grantas.cassio.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.FragmentLogic.ChooseFoodLogic;
import com.example.grantas.cassio.FragmentLogic.CreateFoodLogic;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    Toast mToast;
    ChooseFoodLogic Logic;
    protected List<Food> FoodList ;
    private HorizontalBarChart horizontalChart;


    public FoodExpandableListAdapter(Activity context, List<Food> newList)
    {
        Context = context;

        Logic = new ChooseFoodLogic(context);
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

        final Food item = FoodList.get(groupPosition);
        View view = convertView;
        if (view == null)
        {
            view = Context.getLayoutInflater().inflate(R.layout.food_info_header, null);
        }



        ImageView listHeaderPlusSign = (ImageView)view.findViewById(R.id.greenplus);
        listHeaderPlusSign.setImageResource(R.drawable.green_plus);

        listHeaderPlusSign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Logic.AddLogItem(new LogItem(item, item.Grams, new Date()));
                if(mToast != null) mToast.cancel();
                mToast = Toast.makeText(Context, "Pridėta " + item.Grams + "g " + item.Name, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

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

        LinearLayout listHeaderLayout = (LinearLayout)view.findViewById(R.id.foodheaderlayout);
        int backgroundResourceId = isExpanded ? R.drawable.food_header_border : R.drawable.food_header_border_empty;
        listHeaderLayout.setBackgroundResource(backgroundResourceId);
        listHeaderLayout.setPadding(10, 10, 10, 10);


        ((TextView)view.findViewById(R.id.foodinfoname)).setText(item.Name);
        ((TextView)view.findViewById(R.id.foodinfocalories)).setText(item.Calories + " kal.");
        ((TextView)view.findViewById(R.id.foodinfograms)).setText(item.Grams + " g.");



        return view;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        final Food item = FoodList.get(groupPosition);
        View row = convertView;
        if (row == null)
        {
            row = Context.getLayoutInflater().inflate(R.layout.food_info_child, null);
        }

        ImageView listChildAmount = (ImageView)row.findViewById(R.id.choose_amount_icon);
        listChildAmount.setImageResource(R.drawable.choose_amount_icon);
        ImageView listChildTrashcan = (ImageView)row.findViewById(R.id.redtrashcan);
        listChildTrashcan.setImageResource(R.drawable.red_trash_can);

//        ((TextView)row.findViewById(R.id.foodinfocarbs)).setText(String.format(Locale.getDefault(), "%s: %.2f g.", Context.getString(R.string.carbohydrates), item.Carbohydrates));
//        ((TextView)row.findViewById(R.id.foodinfoprotein)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.protein), item.Protein));
//        ((TextView)row.findViewById(R.id.foodinfofat)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.fat), item.Fat));

        horizontalChart = (HorizontalBarChart) row.findViewById(R.id.horizontalchart);
        setupChart(item);

        listChildAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSelectAmountAlertDialog(item);
            }
        });

        listChildTrashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDeleteAlertDialog(parent, groupPosition, item);
            }
        });

        return row;
    }

    public void setupChart(Food item)
    {
        horizontalChart.getDescription().setEnabled(false);

        horizontalChart.setClickable(false);
        horizontalChart.setPinchZoom(false);
        horizontalChart.getAxisLeft().setDrawLabels(false);
        horizontalChart.getAxisRight().setDrawLabels(false);
        horizontalChart.getXAxis().setDrawLabels(false);
        horizontalChart.getLegend().setEnabled(false);
        horizontalChart.getAxisLeft().setDrawGridLines(false);
        horizontalChart.getXAxis().setEnabled(false);
        horizontalChart.getAxisLeft().setDrawAxisLine(false);
        horizontalChart.getXAxis().setDrawAxisLine(false);
        horizontalChart.getAxisLeft().setEnabled(false);
        horizontalChart.getAxisRight().setEnabled(false);
        horizontalChart.setTouchEnabled(false);
       // horizontalChart.setDrawValueAboveBar(false);

        BarEntry carbentry = new BarEntry(60f, (float)item.Carbohydrates);//(float)item.getCarbohydratesFor100g());
        BarEntry proteinentry = new BarEntry(40f, (float)item.Protein);//(float)item.getProteinFor100g());
        BarEntry fatentry = new BarEntry(20f, (float)item.Fat);//(float)item.getFatFor100g());

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0f, 0));
        entries.add(carbentry);
        entries.add(proteinentry);
        entries.add(fatentry);
        entries.add(new BarEntry(80f, 0));

        BarDataSet set1 = new BarDataSet(entries, "");

        set1.setDrawIcons(false);
        set1.setColors(Color.BLACK, Color.rgb(139, 204, 40), Color.rgb(40, 139, 204), Color.rgb(204, 40, 139), Color.BLACK);
        set1.setValueTextColor(Color.GRAY);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter(new FoodValueFormatter(item));
        data.setValueTextSize(9f);
        data.setBarWidth(19f);

        horizontalChart.setData(data);
        horizontalChart.invalidate();

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

    public void createDeleteAlertDialog(final ViewGroup parent, final int groupPosition, final Food item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setMessage(String.format(Context.getString(R.string.delete_my_food), item.Name));

        builder.setPositiveButton(
                Context.getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((ExpandableListView) parent).collapseGroup(groupPosition);
                        Logic.DeleteFoodItem(item.foodId);
                        UpdateAdapter(Logic.getSortedFoods());
                    }
                }
        );

        builder.setNegativeButton(
                R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void createSelectAmountAlertDialog(final Food item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
       // builder.setView(R.layout.select_amount_dialog);

        LayoutInflater inflater = Context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.select_amount_dialog, null);
        builder.setView(dialogView);

        EditText graminput = (EditText)dialogView.findViewById(R.id.select_dialog_graminput);
        final CheckBox remember = (CheckBox)dialogView.findViewById(R.id.select_dialog_remember);
        final TextView grams = (TextView) dialogView.findViewById(R.id.select_dialog_grams);
        final TextView calories = (TextView)dialogView.findViewById(R.id.select_dialog_calories);

        grams.setText(String.valueOf(item.Grams));
        calories.setText(String.valueOf(item.Calories));

        graminput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    grams.setText(String.valueOf(item.Grams));
                    calories.setText(String.valueOf(item.Calories));
                }
                else
                {
                    grams.setText(s);
                    calories.setText(String.valueOf(Math.round(((double)Integer.parseInt(s.toString()) / (double)item.Grams) * (double)item.Calories)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setPositiveButton(
                Context.getString(R.string.add),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Logic.AddLogItem(new LogItem(item, Integer.parseInt(grams.getText().toString()), new Date()));
                        if(mToast != null) mToast.cancel();
                        mToast = Toast.makeText(Context, "Pridėta " + grams.getText() + "g " + item.Name, Toast.LENGTH_SHORT);
                        mToast.show();
                        if(remember.isChecked())
                        {
                            Logic.UpdateGramsFoodItem(Integer.parseInt(grams.getText().toString()), item);
                            UpdateAdapter(Logic.getSortedFoods());
                        }
                    }
                }
        );

        builder.setNegativeButton(
                R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}


