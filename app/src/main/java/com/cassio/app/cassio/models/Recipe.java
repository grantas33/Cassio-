package com.cassio.app.cassio.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe extends Food implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<RecipeFood> Foods;
    @DatabaseField
    public String RecipeName;

    public Recipe() {
        Grams = 0;
    }

    public Recipe(String name) {
        Grams = 0;
        Name = name;
    }

    public void addFood(Food food, int gramsNeeded) {
        Grams += gramsNeeded;
        RecipeFood foodToAdd = new RecipeFood(food, gramsNeeded);
        Foods.add(foodToAdd);
    }

    @Override
    public int getCalories() {
        int calories = 0;
        for (RecipeFood food : Foods) {
            calories += food.getCalories();
        }
        return calories;
    }

    @Override
    public double getCarbohydrates() {
        int carbs = 0;
        for (RecipeFood food : Foods) {
            carbs += food.getCarbohydrates();
        }
        return carbs;
    }

    @Override
    public double getProtein() {
        int protein = 0;
        for (RecipeFood food : Foods) {
            protein += food.getProtein();
        }
        return protein;
    }

    @Override
    public double getFat() {
        int fat = 0;
        for (RecipeFood food : Foods) {
            fat += food.getCalories();
        }
        return fat;
    }
}
