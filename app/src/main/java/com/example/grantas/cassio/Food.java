package com.example.grantas.cassio;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Grantas on 2017-07-06.
 */

public class Food implements Serializable{

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "food_id")
    public int foodId;

    @DatabaseField(columnName = "name_id")
    public String Name;
    @DatabaseField(columnName = "calories_id")
    public int Calories;
    @DatabaseField(columnName = "grams_id")
    public int Grams;

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
    public Food(){

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

    public String toString()
    {
        return this.Name + ", " + this.Calories + " cal., " + this.Grams + "g.";
    }
}
