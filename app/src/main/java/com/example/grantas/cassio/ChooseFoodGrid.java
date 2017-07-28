package com.example.grantas.cassio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.ChooseFoodLogic;
import com.example.grantas.cassio.Tools.ImageAdapter;

import java.util.Date;

/**
 * Created by Grantas on 2017-07-07.
 */

public class ChooseFoodGrid extends android.support.v4.app.Fragment
{
    private Toast mToast = null;
    private String toastMsg = "";
    ChooseFoodLogic Logic;

    public ChooseFoodGrid()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_food_grid, container, false);
        Logic = new ChooseFoodLogic(getContext());
        GridView gridview = (GridView)view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        ClickAction(Logic.orange, true);
                        break;
                    case 1:
                        ClickAction(Logic.watermelon, false);
                        break;
                    case 2:
                        ClickAction(Logic.banana, true);
                        break;
                    case 3:
                        ClickAction(Logic.pear, false);
                        break;
                    case 4:
                        ClickAction(Logic.tangerine, true);
                        break;
                    case 5:
                        ClickAction(Logic.apple, true);
                        break;
                    case 6:
                        ClickAction(Logic.peach, true);
                        break;
                    case 7:
                        ClickAction(Logic.tomato, true);
                        break;
                    default:
                        break;
                }
            }
        });

        return view;
    }

    private void ClickAction(Food food, boolean male)
    {
        Logic.AddLogItem(new LogItem(food, food.Grams, new Date()));
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getContext(), male ? "Pridėtas " + food.Name : "Pridėta " + food.Name, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
