package com.cassio.app.cassio.fragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.Tools.DatabaseHelper;
import com.cassio.app.cassio.models.LogItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodLogLogic {
    DatabaseHelper databaseHelper = null;
    Context context;

    public FoodLogLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public List<LogItem> getLogItems() {
        List<LogItem> items = new ArrayList<LogItem>();
        try {
            items = getHelper().
                    getLogDao().
                    queryForAll();
            ;
        } catch (SQLException e) {
        }
        //  Collections.sort(items);
        return items;
    }

    public void deleteLogItem(int id) {
        try {
            final Dao<LogItem, Integer> LogItemDao = getHelper().getLogDao();
            LogItemDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        getHelper().clearLogTable();
    }
}
