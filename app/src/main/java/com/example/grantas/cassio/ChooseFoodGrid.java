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
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.SuperToast;

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
                        ClickAction(Logic.cucumber, true);
                        break;
                    case 1:
                        ClickAction(Logic.pineapple, false);
                        break;
                    case 2:
                        ClickAction(Logic.orange, true);
                        break;
                    case 3:
                        ClickAction(Logic.watermelon, false);
                        break;
                    case 4:
                        ClickAction(Logic.avocado, true);
                        break;
                    case 5:
                        ClickAction(Logic.banana, true);
                        break;
                    case 6:
                        ClickAction(Logic.strawberries, false);
                        break;
                    case 7:
                        ClickAction(Logic.kiwi, true);
                        break;
                    case 8:
                        ClickAction(Logic.pear, false);
                        break;
                    case 9:
                        ClickAction(Logic.tangerine, true);
                        break;
                    case 10:
                        ClickAction(Logic.mango, true);
                        break;
                    case 11:
                        ClickAction(Logic.melon, false);
                        break;
                    case 12:
                        ClickAction(Logic.carrot, false);
                        break;
                    case 13:
                        ClickAction(Logic.apple, true);
                        break;
                    case 14:
                        ClickAction(Logic.peach, true);
                        break;
                    case 15:
                        ClickAction(Logic.tomato, true);
                        break;
                    case 16:
                        ClickAction(Logic.plum, false);
                        break;
                    case 17:
                        ClickAction(Logic.grapes, false);
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
        LogItem item = new LogItem(food, food.Grams, new Date());
        Logic.AddLogItem(item);
        ((ChooseFoodTabs)getActivity()).GenerateToast(male, food.Name);
    }




}
