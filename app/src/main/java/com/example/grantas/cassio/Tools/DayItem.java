package com.example.grantas.cassio.Tools;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
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
}
