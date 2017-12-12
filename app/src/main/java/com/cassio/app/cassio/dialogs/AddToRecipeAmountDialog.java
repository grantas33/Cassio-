package com.cassio.app.cassio.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cassio.app.cassio.R;
import com.cassio.app.cassio.fragmentLogic.AddToRecipeLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.Recipe;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddToRecipeAmountDialog {

    public static AlertDialog getAlertDialog(Activity context, Recipe recipe,
                                             AddToRecipeLogic logic, Food food) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_to_recipe_amount_dialog, null);
        builder.setView(view);

        EditText gramInput = (EditText) view.findViewById(R.id.recipe_grams);

        builder.setPositiveButton(context.getString(R.string.add), (dialogInterface, i) -> {
            recipe.addFood(food, Integer.parseInt(gramInput.getText().toString()));
            logic.updateRecipe(recipe);
        });

        builder.setNegativeButton(context.getString(R.string.cancel), (dialogInterface, i) -> {
            dialogInterface.cancel();
        });
        return builder.create();
    }
}
