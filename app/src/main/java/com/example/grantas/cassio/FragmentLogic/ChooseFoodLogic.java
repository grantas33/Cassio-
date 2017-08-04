package com.example.grantas.cassio.FragmentLogic;

import android.content.Context;
import android.os.Debug;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Grantas on 2017-07-10.
 */

public class ChooseFoodLogic {
    DatabaseHelper databaseHelper = null;
    Context context;

    public  ChooseFoodLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public List<Food> getSortedFoods() {
        List<Food> foods = new ArrayList<Food>();
        try
        {
            DatabaseHelper helper = getHelper();
            final Dao<Food, Integer> foodDao = helper.getFoodDao();
                    foods = foodDao.queryForAll();
        }
        catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }
        Collections.sort(foods);
        return foods;
    }

    public void AddLogItem(LogItem item){
        try {

            DatabaseHelper helper = getHelper();
            final Dao<LogItem, Integer> logDao = helper.getLogDao();

            //idedam i duombaze
            logDao.create(item);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
