package com.example.grantas.cassio;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Grantas on 2017-07-07.
 */

public class ChooseFoodGrid extends android.support.v4.app.Fragment
{
    private Toast mToast = null;
    private String toastMsg = "";

    public ChooseFoodGrid()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_food_grid, container, false);

        GridView gridview = (GridView)view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));

        return view;
    }

}
