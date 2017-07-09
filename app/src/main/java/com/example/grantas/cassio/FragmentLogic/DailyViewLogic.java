package com.example.grantas.cassio.FragmentLogic;

import android.content.Context;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 7/7/2017.
 */

public class DailyViewLogic {

    DatabaseHelper databaseHelper = null;
    Context context;

    public  DailyViewLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public List<String> getFoodStrings() {
        List<Food> foods = null;
        List<String> foodsString = new ArrayList<String>();
//        String[] strings;
        try {
            foods = getHelper().getFoodDao().queryForAll();
            for (Food food: foods) {
            foodsString.add(food.toString());
            }
        } catch (SQLException e) {
        }
//        strings = new String[foodsString.size()];
//        for (int i = 0; i < foodsString.size();  i++) {
//            strings[i] = foodsString.get(i);
//        }

        return foodsString;
    }

    public void onDestroy() {
		/*
		 * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
