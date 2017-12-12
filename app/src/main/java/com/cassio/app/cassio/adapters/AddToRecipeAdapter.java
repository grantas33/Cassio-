package com.cassio.app.cassio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cassio.app.cassio.R;
import com.cassio.app.cassio.models.Recipe;
import com.cassio.app.cassio.models.RecipeFood;

import java.util.List;

import static android.support.constraint.R.id.parent;


public class AddToRecipeAdapter extends BaseAdapter {

    Context Context;
    List<Recipe> Recipes;

    public AddToRecipeAdapter(Context context, List<Recipe> recipes) {
        Context = context;
        Recipes = recipes;
    }

    @Override
    public int getCount() {
        if (Recipes != null) return Recipes.size();
        return 0;
    }

    @Override
    public Recipe getItem(int i) {
        return Recipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void updateAdapter(List<Recipe> recipes) {
        Recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Recipe recipe = Recipes.get(i);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(Context).inflate(R.layout.recipe_info, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.recipe_name)).setText(recipe.Name);
        return view;
    }
}
