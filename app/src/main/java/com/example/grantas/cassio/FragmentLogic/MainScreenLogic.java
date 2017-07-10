package com.example.grantas.cassio.FragmentLogic;

import android.content.Context;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.example.grantas.cassio.Tools.LogItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 7/9/2017.
 */

public class MainScreenLogic {

    Context context;
    DatabaseHelper databaseHelper = null;

    public MainScreenLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public int getTotalCalories() {
        int total = 0;
        try {
            final Dao<LogItem, Integer> logDao = getHelper().getLogDao();
//            Food food = new Food("test", 100, 50, 50, 50, 50);
//            LogItem log = new LogItem(food, 50, new Date());
//            databaseHelper.ClearLogTable();
//            logDao.create(log);
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                 ) {
                total += item.getCalories();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }
        return total;
    }

    public double getTotalCarbohydrates() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = databaseHelper.getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getCarbohydrates();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }
        return total;
    }

    public double getTotalProtein() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = databaseHelper.getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getProtein();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }
        return total;
    }

    public double getTotalFat() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = databaseHelper.getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getFat();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }
        return total;
    }
}
