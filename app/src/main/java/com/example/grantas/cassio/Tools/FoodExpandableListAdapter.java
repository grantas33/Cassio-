package com.example.grantas.cassio.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.FragmentLogic.ChooseFoodLogic;
import com.example.grantas.cassio.FragmentLogic.CreateFoodLogic;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;

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

        ((TextView)row.findViewById(R.id.foodinfocarbs)).setText(String.format(Locale.getDefault(), "%s: %.2f g.", Context.getString(R.string.carbohydrates), item.Carbohydrates));
        ((TextView)row.findViewById(R.id.foodinfoprotein)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.protein), item.Protein));
        ((TextView)row.findViewById(R.id.foodinfofat)).setText(String.format(Locale.getDefault(), "%s: %.2f g", Context.getString(R.string.fat), item.Fat));

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


