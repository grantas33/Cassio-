package com.cassio.app.cassio.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class RecipeFood implements Serializable {
    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(id = true)
    public Long id;

    @DatabaseField(foreign = true)
    private Recipe recipe_id;

    @DatabaseField
    public String Name;
    @DatabaseField
    public int Grams;  // Default grams

    //nutrinion is specified per 100g
    @DatabaseField
    private int Calories;
    @DatabaseField
    private double Carbohydrates;
    @DatabaseField
    private double Protein;
    @DatabaseField
    private double Fat;

    public RecipeFood() {
    }

    public RecipeFood(Food food, int gramsNeeded) {
        Name = food.Name;
        Grams = gramsNeeded;
        Calories = food.getDefaultCalories();
        Carbohydrates = food.getDefaultCarbohydrates();
        Protein = food.getDefaultProtein();
        Fat = food.getDefaultFat();
    }

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
}
