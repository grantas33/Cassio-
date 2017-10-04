package com.Cassio.app.cassio;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas on 9/4/2017.
 */

public class Recipe extends ArrayList<Food> implements Serializable { //vis tiek foodus kurs

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "recipeId")
    public int recipeId;

    @DatabaseField(columnName = "foodIds")
    public List<Food> Foods;
    @DatabaseField(columnName = "recipeName")
    public String RecipeName;

    public Recipe () {};

    public Recipe(String name, List<Food> foods) {
        RecipeName = name;
        Foods = foods;
    }
}
