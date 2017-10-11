package com.cassio.app.cassio.fragmentLogic;

import android.content.Context;
import android.widget.Toast;

import com.cassio.app.cassio.tools.DatabaseHelper;
import com.cassio.app.cassio.models.LogItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                 ) {
                total += item.getCalories();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return total;
    }

    public double getTotalCarbohydrates() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = getHelper().getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getCarbohydrates();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return total;
    }

    public double getTotalProtein() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = getHelper().getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getProtein();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return total;
    }

    public double getTotalFat() {
        double total = 0;
        try {
            final Dao<LogItem, Integer> logDao = getHelper().getLogDao();
            List<LogItem> items = logDao.queryForAll();
            for (LogItem item: items
                    ) {
                total += item.getFat();
            }
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return total;
    }
}
