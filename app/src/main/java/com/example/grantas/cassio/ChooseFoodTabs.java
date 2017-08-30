package com.example.grantas.cassio;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.grantas.cassio.Tools.FragmentAdapter;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class ChooseFoodTabs extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mPager;
    public SuperActivityToast mSuper = null;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_choose_food);

        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.choose_food));

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);


    }
    //Kad veiktu mygtukas virsuj desinej "<-"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void GenerateToast(boolean male, String name)
    {
        if(mSuper!=null) mSuper.dismiss();
        mSuper = (SuperActivityToast) SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                .setFrame(Style.FRAME_KITKAT)
                .setText(male ? "Pridėtas " + name : "Pridėta " + name)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP);
        mSuper.show();
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if(mSuper!=null) mSuper.dismiss();
    }
}
