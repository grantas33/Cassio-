package com.cassio.app.cassio.models;

import com.j256.ormlite.field.DatabaseField;

public class RecipeFood extends Food {
    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "recipe_food_id")
    public int recipeFoodId;

    @DatabaseField(columnName = "grams_needed_id")
    public int GramsNeeded;

    public RecipeFood() {
    }

    public RecipeFood(Food food, int gramsNeeded) {
        super(food.Name, food.getDefaultCalories(), food.Grams, food.getDefaultCarbohydrates(),
                food.getDefaultProtein(), food.getDefaultFat());
        GramsNeeded = gramsNeeded;
    }

    private double getNutritionInGrams(double defaultGrams, double nutrition, int recipeGrams) {
        return (nutrition * recipeGrams) / defaultGrams;
    }
}
