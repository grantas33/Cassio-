package com.cassio.app.cassio.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cassio.app.cassio.ChooseFoodGrid;
import com.cassio.app.cassio.ChooseFoodList;


/**
 * Created by Grantas on 2017-07-06.
 * Klase, isdestanti fragmentus "Choose food"
 */

public class FragmentAdapter extends FragmentStatePagerAdapter
{

    public FragmentAdapter(FragmentManager fm){
        super(fm);
    }
    ChooseFoodGrid FruitsAndVegetables = new ChooseFoodGrid();
    ChooseFoodGrid MilkProducts = new ChooseFoodGrid();
    ChooseFoodGrid GrainProducts = new ChooseFoodGrid();
    Bundle bundle = new Bundle();



    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ChooseFoodList();
            case 1:
                bundle = new Bundle();
                bundle.putInt("index", 0);
                FruitsAndVegetables.setArguments(bundle);
                return FruitsAndVegetables;
            case 2:
                bundle = new Bundle();
                bundle.putInt("index", 1);
                MilkProducts.setArguments(bundle);
                return MilkProducts;
            case 3:
                bundle = new Bundle();
                bundle.putInt("index", 2);
                GrainProducts.setArguments(bundle);
                return GrainProducts;
            default:
                return new ChooseFoodList();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Mano produktai";
            case 1:
                return "Vaisiai ir daržovės";
            case 2:
                return "Pieno produktai";
            case 3:
                return "Grūdiniai produktai";
            default:
                return null;
        }
    }
}
