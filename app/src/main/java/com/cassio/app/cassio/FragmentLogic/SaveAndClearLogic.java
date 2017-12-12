package com.cassio.app.cassio.FragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.models.LogItem;
import com.cassio.app.cassio.Tools.DatabaseHelper;
import com.cassio.app.cassio.models.DayItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Atskira logikos klase naudojama SaveAndClearDialog ir AlarmReceiver
 */

public class SaveAndClearLogic {
    Context context;
    DatabaseHelper databaseHelper = null;

    public SaveAndClearLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void clearLogs() {
        getHelper().clearLogTable();
    }

    public void saveLogs(boolean isYesterday) {
        List<LogItem> logs;
        int calories = 0;
        double carbohydrates = 0;
        double protein = 0;
        double fat = 0;
        Date date;
        if(isYesterday)
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            date = cal.getTime();
        }
        else {date = new Date();}

        try {
            logs = getHelper().getLogDao().queryForAll();
            for (LogItem log:
                    logs) {
                calories += log.getCalories();
                carbohydrates += log.getCarbohydrates();
                protein += log.getProtein();
                fat += log.getFat();
            }
            DayItem dayitem = new DayItem(date, calories, carbohydrates, protein, fat);
            addDayItem(dayitem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDayItem(DayItem dayItem) {
        try {
            DatabaseHelper helper = getHelper();
            final Dao<DayItem, Integer> dao = helper.getDayDao();
            dao.create(dayItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
