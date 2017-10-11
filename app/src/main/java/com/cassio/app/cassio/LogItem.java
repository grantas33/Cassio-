package com.cassio.app.cassio;

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

//    @DatabaseField(dataType = DataType.SERIALIZABLE, foreign = true, foreignAutoCreate = true, foreignAutoRefresh=true, columnName = "fooditem_id")
//    public Food Fooditem; //maistas
    @DatabaseField(columnName = "logname_id")
    public String FoodName;
    @DatabaseField(columnName = "logcalories_id")
    public int Calories;
    @DatabaseField(columnName = "logdefaultgrams_id")
    public int DefaultGrams;  // Default grams
    @DatabaseField(columnName = "logcarbohydrates_id")
    public double Carbohydrates;
    @DatabaseField(columnName = "logprotein_id")
    public double Protein;
    @DatabaseField(columnName = "logfat_id")
    public double Fat;
    @DatabaseField(columnName = "loggrams_id")
    public int Grams; //kiek valge
    @DatabaseField(columnName = "logtime_id")
    public Date Time; //kada valge

    public LogItem() {

    }

//    public LogItem(Food fooditem, int grams, Date time) {
//        Fooditem = fooditem;
//        Grams = grams;
//        Time = time;
//    }

    public LogItem(Food fooditem, int grams, Date time) {
        FoodName = fooditem.Name;
        Calories = fooditem.Calories;
        DefaultGrams = fooditem.Grams;
        Carbohydrates = fooditem.Carbohydrates;
        Protein = fooditem.Protein;
        Fat = fooditem.Fat;
        Grams = grams;
        Time = time;
    }


    public int getCalories() { return (int) Math.round(((double)Grams * (double)Calories) / (double)DefaultGrams); }

    public double getCarbohydrates() {
        return (Grams * Carbohydrates) / DefaultGrams;
    }

    public double getProtein() {
        return (Grams * Protein) / DefaultGrams;
    }

    public double getFat() { return (Grams * Fat) / DefaultGrams; }
}
