package com.cassio.app.cassio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cassio.app.cassio.FragmentLogic.MainActivityLogic;
import com.cassio.app.cassio.dialogs.SaveAndClearDialog;
import com.cassio.app.cassio.models.Food;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.OnClick;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Fragment fragment = null;
    private boolean viewIsAtHome;
    MainActivityLogic Logic;
    public int currentViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        onInstall();                             //jei pirma kart runnina po installo, uzdeda aliarma istrint food logui

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        displayView(R.id.main_screen); //set main screen
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        Logic = new MainActivityLogic(this);

        if (ChooseFoodListFragment.foodNameExtra != null) {
            displayView(R.id.create_food);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("menu_item", currentViewId);
    }

    @OnClick(R.id.fab)
    public void submit(View view) {
        displayView(R.id.choose_food);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!viewIsAtHome) {
            displayView(R.id.main_screen);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //triple dots in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            viewIsAtHome = false;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displayView(id);
        return true;
    }

    private void hideFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    private void showFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
    }

    public void displayView(int viewId) {
        if (viewId != R.id.save_and_clear) {
            currentViewId = viewId;
        }

        if (viewId == R.id.create_food) {
            hideFab();
        } else {
            showFab();
        }

        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.choose_food:
                Intent intent = new Intent(MainActivity.this, ChooseFoodTabs.class);
                startActivity(intent);
                viewIsAtHome = false;
                break;
            case R.id.create_food:
                fragment = new CreateFoodFragment();
                title = getString(R.string.new_food);
                viewIsAtHome = false;
                break;
            case R.id.create_recipe:
                fragment = new CreateRecipeFragment();
                title = getString(R.string.view_recipes);
                viewIsAtHome = false;
                break;
            case R.id.main_screen:
                fragment = new MainScreenFragment();
                title = getString(R.string.home_screen);
                viewIsAtHome = true;
                break;
            case R.id.daily_view:
                fragment = new DailyViewFragment();
                title = getString(R.string.daily_view);
                viewIsAtHome = false;
                break;
            case R.id.food_log:
                fragment = new FoodLogFragment();
                title = getString(R.string.food_log);
                viewIsAtHome = false;
                break;
            case R.id.save_and_clear:
                SaveAndClearDialog dialog = new SaveAndClearDialog(this);
                dialog.createAlertDialog();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "TAG");
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public void scanBarcode(View view) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    //called when barcode is found
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Atšaukta", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Logic.processBarcode(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onInstall() {
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            SettingsActivity instance = new SettingsActivity();
            instance.checkAlarm(wmbPreference, getApplicationContext());
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }
    }
}
