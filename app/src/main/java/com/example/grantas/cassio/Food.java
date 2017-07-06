package com.example.grantas.cassio;

/**
 * Created by Grantas on 2017-07-06.
 */

public class Food {

    public String Name;
    public int Calories;
    public int Grams;
    public double Carbohydrates;
    public double Protein;
    public double Fat;

    public Food(String name, int calories, int grams)
    {
        Name = name;
        Calories = calories;
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

    public String toString()
    {
        return this.Name + ", " + this.Calories + " cal., " + this.Grams + "g.";
    }
}
