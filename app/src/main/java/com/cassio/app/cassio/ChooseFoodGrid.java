package com.cassio.app.cassio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cassio.app.cassio.fragmentLogic.ChooseFoodLogic;
import com.cassio.app.cassio.tools.DefaultFood;
import com.cassio.app.cassio.tools.Dialogs.AdvancedFoodAddDialog;
import com.cassio.app.cassio.tools.ImageAdapter;

import java.util.Date;

/**
 * Created by Grantas on 2017-07-07.
 */

public class ChooseFoodGrid extends android.support.v4.app.Fragment
{
    private Toast mToast = null;
    ChooseFoodLogic Logic;
    int index;

    public ChooseFoodGrid()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index", 0);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_food_grid, container, false);
        Logic = new ChooseFoodLogic(getContext());
        GridView gridview = (GridView)view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext(), DefaultFood.LogoArrays[index]));


        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AdvancedFoodAddDialog.getDialog(getActivity(), DefaultFood.DataArrays[index][position]).show();
                return true;
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClickAction(DefaultFood.DataArrays[index][position]);
            }
        });

        return view;
    }

    private void ClickAction(Food food)
    {
        LogItem item = new LogItem(food, food.Grams, new Date());
        Logic.AddLogItem(item);
        if (mToast != null) mToast.cancel();
        ((ChooseFoodTabs)getContext()).GenerateToast(food.Name);
    }

}
