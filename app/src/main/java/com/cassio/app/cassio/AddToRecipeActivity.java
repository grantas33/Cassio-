package com.cassio.app.cassio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cassio.app.cassio.adapters.AddToRecipeAdapter;
import com.cassio.app.cassio.dialogs.AddToRecipeAmountDialog;
import com.cassio.app.cassio.dialogs.CreateRecipeDialog;
import com.cassio.app.cassio.fragmentLogic.AddToRecipeLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.Recipe;

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
    @BindView(R.id.create_recipe_ne)
    Button CreateRecipe;
    @BindView(R.id.empty_recipes)
    View EmptyRecipesView;

    AddToRecipeAdapter adapter;
    Food Food;

    Activity context = this;

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
        RecipeList.setOnItemClickListener(onItemClickListener);
    }

    @OnClick(R.id.create_recipe)
    public void createRecipe() {
        CreateRecipeDialog.createRecipeDialog(this, Logic, adapter);
    }

    @OnClick(R.id.create_recipe_ne)
    public void setCreateRecipeNe() {
        createRecipe();
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

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Recipe recipe = adapter.getItem(i);
            AlertDialog dialog = AddToRecipeAmountDialog.getAlertDialog(context, recipe, Logic, Food);
            dialog.show();
//            recipe.addFood();
        }
    };
}
