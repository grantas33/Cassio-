package com.example.grantas.cassio.Tools;

import com.example.grantas.cassio.R;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lukas on 7/19/2017.
 */

public class DayItem implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "day_id")
    public int dayId;

    @DatabaseField(columnName = "date_id")
    Date Date;
    @DatabaseField(columnName = "calories_id")
    int Calories;
    @DatabaseField(columnName = "carbohydrates_id")
    double Carbohydrates;
    @DatabaseField(columnName = "protein_id")
    double Protein;
    @DatabaseField(columnName = "fat_id")
    double Fat;


    public DayItem() {

    }
    public DayItem(Date date, int calories, double carbohydrates, double protein, double fat) {
        Date = date;
        Calories = calories;
        Carbohydrates = carbohydrates;
        Protein = protein;
        Fat = fat;
    }

    private int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date); // don't forget this if date is arbitrary e.g. 01-01-2014

        return cal.get(Calendar.YEAR);
//        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 6
//        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); // 17
//        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR); //169
//
//        int month = cal.get(Calendar.MONTH); // 5
//        int year = cal.get(Calendar.YEAR); // 2016
    }

    public String getFullDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(Date);
    }

    private int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date); // don't forget this if date is arbitrary e.g. 01-01-2014

        return cal.get(Calendar.MONTH);
    }

    public String getYearMonth() {
        return getYear() + " - " + String.valueOf(getMonth() + 1);
    }

    public String getCalories() {
        return String.valueOf(Calories);
    }

    public double getCarbohydratePercent() { return Carbohydrates/(Carbohydrates + Protein + Fat) * 100; }
    public double getProteinPercent() { return Protein/(Carbohydrates + Protein + Fat) * 100; }
    public double getFatPercent() { return Fat/(Carbohydrates + Protein + Fat) * 100; }
}
