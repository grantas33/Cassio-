package com.cassio.app.cassio;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cassio.app.cassio.adapters.FragmentAdapter;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseFoodTabs extends AppCompatActivity {

    private Toolbar toolbar;
    @BindView(R.id.tabs)
     TabLayout tabLayout;
    @BindView(R.id.pager)
     ViewPager mPager;
    public SuperActivityToast mSuper = null;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_choose_food);
        ButterKnife.bind(this);

        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.choose_food));

        mPager.setAdapter(mAdapter);

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

    public void generateToast(String name) {
        if (mSuper != null) mSuper.dismiss();
        mSuper = (SuperActivityToast) SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                .setFrame(Style.FRAME_KITKAT)
                .setText("Pridėta: " + name)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP);
        mSuper.show();
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSuper != null) mSuper.dismiss();
    }
}
