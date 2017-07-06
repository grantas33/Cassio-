package com.example.grantas.cassio.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.grantas.cassio.Food;
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
    private static final int DATABASE_VERSION = 1;

    private Dao<Food, Integer> foodDao;


    public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
            try {
                //Create tables
                TableUtils.createTable(connectionSource, Food.class);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
            try{
                TableUtils.dropTable(connectionSource, Food.class, true);
                onCreate(sqLiteDatabase, connectionSource);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                        + newVer, e);
            }
        }

        public Dao<Food, Integer> getFoodData() throws SQLException {
            if (foodDao != null) {
                foodDao = getDao(Food.class);
            }
            return foodDao;
        }
    }
