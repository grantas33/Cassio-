package com.example.grantas.cassio;

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

    @DatabaseField(dataType = DataType.SERIALIZABLE, foreign = true, foreignAutoCreate = false, foreignAutoRefresh=false, columnName = "fooditem_id")
    public Food Fooditem; //maistas
    @DatabaseField(columnName = "grams_id")
    public int Grams; //kiek valge
    @DatabaseField(columnName = "time_id")
    public Date Time; //kada valge

    public LogItem() {

    }

    public LogItem(Food fooditem, int grams, Date time) {
        Fooditem = fooditem;
        Grams = grams;
        Time = time;
    }

    public int getCalories() {
        if (Fooditem == null) return 0;
        return Math.round((Grams * Fooditem.Calories) / Fooditem.Grams);
    }

    public double getCarbohydrates() {
        return (Grams * Fooditem.Carbohydrates) / Fooditem.Grams;
    }

    public double getProtein() {
        return (Grams * Fooditem.Protein) / Fooditem.Grams;
    }

    public double getFat() {
        return (Grams * Fooditem.Fat) / Fooditem.Grams;
    }
}
