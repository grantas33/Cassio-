package com.cassio.app.cassio.fragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.Tools.DatabaseHelper;
import com.cassio.app.cassio.models.DayItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyViewLogic {

    DatabaseHelper databaseHelper = null;
    Context context;

    public DailyViewLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
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
