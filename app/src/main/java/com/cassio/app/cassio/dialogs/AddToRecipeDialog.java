package com.cassio.app.cassio.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.cassio.app.cassio.R;
import com.cassio.app.cassio.adapters.FoodExpandableListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddToRecipeDialog {

    @BindView(R.id.choose_food_recipe_dialog)
    ListView AllFoodsListView;

    public static AlertDialog getAddToRecipeDialog(final Activity context,
                                                   final FoodExpandableListAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_to_recipe_dialog, null);
        builder.setView(view);
        ButterKnife.bind(view);

        return builder.create();
    }
}
