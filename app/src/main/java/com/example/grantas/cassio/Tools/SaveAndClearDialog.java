package com.example.grantas.cassio.Tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.SaveAndClearLogic;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.MainActivity;
import com.example.grantas.cassio.R;
import com.example.grantas.cassio.SettingsActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 7/19/2017.
 */

public class SaveAndClearDialog {
    MainActivity context;
    //DatabaseHelper databaseHelper = null;
    SaveAndClearLogic Logic;

    public SaveAndClearDialog(MainActivity context) {
        this.context = context;
        Logic = new SaveAndClearLogic(context);
    }

//    private DatabaseHelper getHelper() {
//        if(databaseHelper == null)
//        {
//            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
//        }
//        return databaseHelper;
//    }

    public void createAlertDialog() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        boolean autoSavePref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_AUTOSAVE, false);
        if(autoSavePref)
        {
            Toast.makeText(context.getApplicationContext(), R.string.autosave_information_alert, Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.confirm_save_and_clear);

            builder.setPositiveButton(
                    context.getString(R.string.yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Logic.saveLogs();
                            Logic.clearLogs();
                            dialog.cancel();
                            context.displayView(context.currentViewId);
                        }
                    }
            );

            builder.setNegativeButton(
                    R.string.no,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }
            );

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

//    private void clearLogs() {
//        getHelper().clearLogTable();
//    }
//
//    private void saveLogs() {
//        List<LogItem> logs;
//        int calories = 0;
//        double carbohydrates = 0;
//        double protein = 0;
//        double fat = 0;
//        Date date = new Date();
//        try {
//            logs = getHelper().getLogDao().queryForAll();
//            for (LogItem log:
//                 logs) {
//                calories += log.getCalories();
//                carbohydrates += log.getCarbohydrates();
//                protein += log.getProtein();
//                fat += log.getFat();
//            }
//            DayItem dayitem = new DayItem(date, calories, carbohydrates, protein, fat);
//            addDayItem(dayitem);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addDayItem(DayItem dayItem) {
//        try {
//            DatabaseHelper helper = getHelper();
//            final Dao<DayItem, Integer> dao = helper.getDayDao();
//            dao.create(dayItem);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
