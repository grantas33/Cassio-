package com.cassio.app.cassio.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayItem implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "day_id")
    public int dayId;

    @DatabaseField(columnName = "date_id")
    public Date Date;
    @DatabaseField(columnName = "calories_id")
    public int Calories;
    @DatabaseField(columnName = "carbohydrates_id")
    public double Carbohydrates;
    @DatabaseField(columnName = "protein_id")
    public double Protein;
    @DatabaseField(columnName = "fat_id")
    public double Fat;


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

    public double getCarbohydratePercent() {
        return Carbohydrates / (Carbohydrates + Protein + Fat) * 100;
    }

    public double getProteinPercent() {
        return Protein / (Carbohydrates + Protein + Fat) * 100;
    }

    public double getFatPercent() {
        return Fat / (Carbohydrates + Protein + Fat) * 100;
    }
}
