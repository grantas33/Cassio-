package com.cassio.app.cassio.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.cassio.app.cassio.R;
import com.cassio.app.cassio.adapters.AddToRecipeAdapter;
import com.cassio.app.cassio.FragmentLogic.AddToRecipeLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.Recipe;

public class CreateRecipeDialog {
    public static void createRecipeDialog(Activity context, AddToRecipeLogic logic, AddToRecipeAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = context.getLayoutInflater().inflate(R.layout.create_recipe_dialog, null);
        builder.setView(view);
        EditText recipeName = (EditText) view.findViewById(R.id.create_recipe_name);

        builder.setPositiveButton("Sukurti",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Recipe recipe = new Recipe(recipeName.getText().toString());
                        logic.addRecipe(recipe);
                        adapter.updateAdapter(logic.getRecipes());
                    }
                });

        builder.setNegativeButton(
                R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        builder.create().show();
    }
}
