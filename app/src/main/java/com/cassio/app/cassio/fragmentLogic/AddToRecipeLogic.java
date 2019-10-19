package com.cassio.app.cassio.fragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.Tools.DatabaseHelper;
import com.cassio.app.cassio.adapters.AddToRecipeAdapter;
import com.cassio.app.cassio.models.Recipe;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class AddToRecipeLogic {

    Context Context;
    DatabaseHelper databaseHelper = null;

    public AddToRecipeLogic(Context context) {
        Context = context;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(Context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = null;
        try {
            recipes = getHelper().getRecipeDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        try {
            getHelper().getRecipeDao().create(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecipe(Recipe recipe) {
        try {
            getHelper().getRecipeDao().update(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
