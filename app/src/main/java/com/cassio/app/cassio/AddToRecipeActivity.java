package com.cassio.app.cassio;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cassio.app.cassio.adapters.AddToRecipeAdapter;
import com.cassio.app.cassio.dialogs.CreateRecipeDialog;
import com.cassio.app.cassio.fragmentLogic.AddToRecipeLogic;
import com.cassio.app.cassio.models.Food;

import java.io.Console;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToRecipeActivity extends AppCompatActivity{

    AddToRecipeLogic Logic;

    @BindView(R.id.recipe_list)
    ListView RecipeList;
    @BindView(R.id.create_recipe)
    Button CreateRecipeButton;
    @BindView(R.id.empty_recipes)
    View EmptyRecipesView;

    AddToRecipeAdapter adapter;
    Food Food;

    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.add_to_recipe);
        ButterKnife.bind(this);
        Logic = new AddToRecipeLogic(this);
        Food = (Food) getIntent().getSerializableExtra("EXTRA_FOOD");

        adapter = new AddToRecipeAdapter(this, Logic.getRecipes());
        RecipeList.setAdapter(adapter);
        RecipeList.setEmptyView(EmptyRecipesView);
    }

    @OnClick(R.id.create_recipe)
    public void createRecipe() {
        CreateRecipeDialog.createRecipeDialog(this, Logic, adapter);
    }

    //Kad veiktu mygtukas virsuj kairej "<-"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
