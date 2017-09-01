package com.example.grantas.cassio.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

    /**
     * Created by Lukas on 7/6/2017.
     */

    public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

        private static final String DATABASE_NAME = "fooddir.db";
        private static final int DATABASE_VERSION = 7;

        private Dao<Food, Integer> foodDao;
        private Dao<LogItem, Integer> logDao;
        private Dao<DayItem, Integer> dayDao;


        public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
            try {
                //Create tables
                TableUtils.createTable(connectionSource, LogItem.class);
                TableUtils.createTable(connectionSource, Food.class);
                TableUtils.createTable(connectionSource, DayItem.class);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
            try{
                TableUtils.dropTable(connectionSource, LogItem.class, true);
                TableUtils.dropTable(connectionSource, Food.class, true);
                TableUtils.dropTable(connectionSource, DayItem.class, true);
                onCreate(sqLiteDatabase, connectionSource);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                        + newVer, e);
            }
        }

        public void clearLogTable() {
            try {
                TableUtils.clearTable(getConnectionSource(), LogItem.class);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to delete databases", e);
            }
        }

        public void clearDaysTable() {
            try {
                TableUtils.clearTable(getConnectionSource(), DayItem.class);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to delete databases", e);
            }
        }

        //add or get food list using dao
        public Dao<Food, Integer> getFoodDao() throws SQLException {
            if (foodDao == null) {
                foodDao = getDao(Food.class);
            }
            return foodDao;
        }

        public Dao<LogItem, Integer> getLogDao() throws SQLException {
            if (logDao == null) {
                logDao = getDao(LogItem.class);
            }
            return logDao;
        }

        public Dao<DayItem, Integer> getDayDao() throws SQLException {
            if (dayDao == null) {
                dayDao = getDao(DayItem.class);
            }
            return dayDao;
        }
    }
