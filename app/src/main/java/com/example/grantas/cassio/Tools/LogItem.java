package com.example.grantas.cassio.Tools;

import android.util.Log;

import com.example.grantas.cassio.Food;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 7/9/2017.
 */

public class LogItem implements Serializable{

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "log_id")
    public int logId;

    @DatabaseField(columnName = "food_id")
    Food Name; //maisto pavadinimas
    @DatabaseField(columnName = "grams_id")
    int Grams; //kiek valge
    @DatabaseField(columnName = "time_id")
    Date Time; //kada valge

    public LogItem(Food name, int grams, Date time) {
        Name = name;
        Grams = grams;
        Time = time;
    }
}
