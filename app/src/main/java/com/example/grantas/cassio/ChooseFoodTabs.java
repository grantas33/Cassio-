package com.example.grantas.cassio;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.SupportActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;

public class ChooseFoodTabs extends FragmentActivity {


    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_choose_food);

        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager());

        ViewPager mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

}
