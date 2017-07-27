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
                        ClickAction(new Food("Apelsinas", 87, 184, 21.6, 1.7, 0.2), true);
                        break;
                    case 1:
                        ClickAction(new Food("Arbūzo riekė (1/16 arbūzo)", 86, 286, 21.6, 1.7, 0.4), false);
                        break;
                    case 2:
                        ClickAction(new Food("Bananas", 105, 118, 27, 1.3, 0.4), true);
                        break;
                    case 3:
                        ClickAction(new Food("Kriaušė", 103, 178, 27.5, 0.7, 0.2), false);
                        break;
                    case 4:
                        ClickAction(new Food("Obuolys", 95, 182, 25.1, 0.5, 0.3), true);
                        break;
                    case 5:
                        ClickAction(new Food("Pomidoras", 22, 123, 4.8, 1.1, 0.2), true);
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
