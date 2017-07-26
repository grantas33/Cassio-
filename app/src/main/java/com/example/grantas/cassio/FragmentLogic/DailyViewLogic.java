package com.example.grantas.cassio.FragmentLogic;

import android.content.Context;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.example.grantas.cassio.Tools.DayItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.io.Console;
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

    public List<DayItem> getDays() {
        List<DayItem> days = new ArrayList<DayItem>();
        try {
            days = getHelper().getDayDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }

    public void clearDays() {
        getHelper().clearDaysTable();
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
