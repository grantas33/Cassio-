package com.cassio.app.cassio.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable { //vis tiek foodus kurs

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(id = true)
    public Long id;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<RecipeFood> Foods;
    @DatabaseField
    public String RecipeName;

    public Recipe() {
    }

    public Recipe(String name) {
        RecipeName = name;
    }

    public void addFood(Food food, int gramsNeeded) {
        RecipeFood foodToAdd = new RecipeFood(food, gramsNeeded);
        Foods.add(foodToAdd);
    }
}
