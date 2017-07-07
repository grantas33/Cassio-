package com.example.grantas.cassio.Tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grantas.cassio.ChooseFoodGrid;


/**
 * Created by Grantas on 2017-07-06.
 * Klase, isdestanti fragmentus "Choose food"
 */

public class FragmentAdapter extends FragmentPagerAdapter
{

    public FragmentAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ChooseFoodGrid();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Vaisiai ir daržovės";
            default:
                return null;
//            case 1:
//                return "Mano produktai";
        }
    }
}
