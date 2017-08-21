package com.example.grantas.cassio;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Grantas on 2017-07-06.
 */
@DatabaseTable(tableName = "logitem")
public class Food implements Serializable, Comparable<Food>{

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "food_id")
    public int foodId;

    @DatabaseField(columnName = "name_id")
    public String Name;
    @DatabaseField(columnName = "calories_id")
    public int Calories;
    @DatabaseField(columnName = "grams_id")
    public int Grams;  // Default grams

    @DatabaseField(columnName = "carbohydrates_id")
    public double Carbohydrates;
    @DatabaseField(columnName = "protein_id")
    public double Protein;
    @DatabaseField(columnName = "fat_id")
    public double Fat;

    public Food(String name, int calories, int grams)
    {
        Name = name;
        Calories = calories;
        Grams = grams;
    }

    //need this for SQLite
    public Food() {

    }

    public Food(String name, int grams) {
        Name = name;
        Grams = grams;
    }

    public Food(String name, int calories, int grams, double carbohydrates, double protein, double fat)
    {
        Name = name;
        Calories = calories;
        Grams = grams;
        Carbohydrates = carbohydrates;
        Protein = protein;
        Fat = fat;
    }

    //for updating food grams inside database
    public Food(int oldgrams, int id, String name, int calories, int newgrams, double carbohydrates, double protein, double fat)
    {
        foodId = id;
        Name = name;
        Calories = (int)Math.round((double)calories * ((double) newgrams / (double) oldgrams));
        Grams = newgrams;
        Carbohydrates = carbohydrates * ((double) newgrams / (double) oldgrams);
        Protein = protein * ((double) newgrams / (double) oldgrams);
        Fat = fat * ((double) newgrams / (double) oldgrams);
    }

    public double getCarbohydratesFor100g() {return Carbohydrates * 100 / (double)Grams ;}
    public double getProteinFor100g() {return Protein * 100 / (double)Grams ;}
    public double getFatFor100g() {return Fat * 100 / (double)Grams ;}

    public String toString()
    {
        return this.Name + ", " + this.Calories + " cal., " + this.Grams + "g.";
    }

    @Override
    public int compareTo(@NonNull Food o) {
        return this.Name.compareTo(o.Name);
    }
}
