package com.example.grantas.cassio.Tools;

import android.util.Log;

import com.example.grantas.cassio.Food;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lukas on 7/9/2017.
 */

public class LogItem implements Serializable{

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "log_id")
    public int logId;

    @DatabaseField(dataType = DataType.SERIALIZABLE, foreign = true, foreignAutoCreate = true, columnName = "name_id")
    public Food Name; //maisto pavadinimas
    @DatabaseField(columnName = "grams_id")
    public int Grams; //kiek valge
    @DatabaseField(columnName = "time_id")
    public Date Time; //kada valge

    public LogItem() {

    }

    public LogItem(Food name, int grams, Date time) {
        Name = name;
        Grams = grams;
        Time = time;
    }

    public int getCalories() {
        return Math.round((Grams * Name.Calories) / Name.Grams);
    }

    public double getCarbohydrates() {
        return (Grams * Name.Carbohydrates) / Name.Grams;
    }

    public double getProtein() {
        return (Grams * Name.Protein) / Name.Grams;
    }

    public double getFat() {
        return (Grams * Name.Fat) / Name.Grams;
    }
}
