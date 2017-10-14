package com.cassio.app.cassio.models;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "logitem")
public class Food implements Serializable, Comparable<Food> {

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "food_id")
    public int foodId;

    @DatabaseField(columnName = "name_id")
    public String Name;
    @DatabaseField(columnName = "grams_id")
    public int Grams;  // Default grams

    //nutrinion is specified per 100g
    @DatabaseField(columnName = "calories_id")
    private int Calories;
    @DatabaseField(columnName = "carbohydrates_id")
    private double Carbohydrates;
    @DatabaseField(columnName = "protein_id")
    private double Protein;
    @DatabaseField(columnName = "fat_id")
    private double Fat;

    public Food(String name, int calories, int grams) {
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

    public Food(String name, int calories, int grams, double carbohydrates, double protein, double fat) {
        Name = name;
        Calories = calories;
        Grams = grams;
        Carbohydrates = carbohydrates;
        Protein = protein;
        Fat = fat;
    }

    //for updating food grams inside database
//    public Food(int oldgrams, int id, String name, int calories, int newgrams, double carbohydrates, double protein, double fat) {
//        foodId = id;
//        Name = name;
//        Calories = (int) Math.round((double) calories * ((double) newgrams / (double) oldgrams));
//        Grams = newgrams;
//        Carbohydrates = carbohydrates * ((double) newgrams / (double) oldgrams);
//        Protein = protein * ((double) newgrams / (double) oldgrams);
//        Fat = fat * ((double) newgrams / (double) oldgrams);
//    }

    public void setGrams(int grams) {
        Grams = grams;
    }

    public int getDefaultCalories() {
        return Calories;
    }

    public int getCalories() {
        return Calories * Grams / 100;
    }

    public int getCaloriesPerGrams(int grams) {
        return Calories * grams / 100;
    }

    public double getDefaultCarbohydrates() {
        return Carbohydrates;
    }

    public double getCarbohydrates() {
        return round(Carbohydrates * Grams / 100);
    }

    public double getCarbohydratesPerGrams(int grams) {
        return round(Carbohydrates * grams / 100);
    }

    public double getProtein() {
        return round(Protein * Grams / 100);
    }

    public double getDefaultProtein() {
        return Protein;
    }

    public double getProteinPerGrams(int grams) {
        return round(Protein * grams / 100);
    }

    public double getFat() {
        return round(Fat * Grams / 100);
    }

    public double getDefaultFat() {
        return Fat;
    }

    public double getFatPerGrams(int grams) {
        return round(Fat * grams / 100);
    }

    //round up to 3 decimal places
    private double round(double value) {
        return Math.round(value * 1000.0000) / 1000.0000;
    }

    public String toString() {
        return this.Name + ", " + this.Calories + " cal., " + this.Grams + "g.";
    }

    @Override
    public int compareTo(@NonNull Food o) {
        return this.Name.compareTo(o.Name);
    }
}
