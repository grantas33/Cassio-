package com.cassio.app.cassio.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cassio.app.cassio.ChooseFoodTabs;
import com.cassio.app.cassio.Tools.DatabaseHelper;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.LogItem;
import com.cassio.app.cassio.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

public class AdvancedFoodAddDialog {

    public static AlertDialog getDialog(final Activity context, final Food food) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.default_food_amount_dialog, null);
        builder.setView(dialogLayout);
        builder.setCancelable(true);

        final TextView foodname = (TextView) dialogLayout.findViewById(R.id.default_name);
        final TextView carbohydrates = (TextView) dialogLayout.findViewById(R.id.default_carbohydrates);
        final TextView protein = (TextView) dialogLayout.findViewById(R.id.default_protein);
        final TextView fat = (TextView) dialogLayout.findViewById(R.id.default_fat);
        final TextView calories = (TextView) dialogLayout.findViewById(R.id.default_calories);
        final EditText gramInput = (EditText) dialogLayout.findViewById(R.id.customGrams);

        foodname.setText(food.Name);
        gramInput.append(String.valueOf(food.Grams));
        int grams = Integer.parseInt(gramInput.getText().toString());

        carbohydrates.setText(String.valueOf(food.getCarbohydratesPerGrams(grams)));
        calories.setText(String.valueOf(food.getCaloriesPerGrams(grams)));
        protein.setText(String.valueOf(food.getProteinPerGrams(grams)));
        fat.setText(String.valueOf(food.getFatPerGrams(grams)));

        gramInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    carbohydrates.setText(String.valueOf(food.getDefaultCarbohydrates()));
                    calories.setText(String.valueOf(food.getDefaultCalories()));
                    protein.setText(String.valueOf(food.getDefaultProtein()));
                    fat.setText(String.valueOf(food.getDefaultFat()));
                } else {
                    int grams = (int) Math.round(((double) Integer.parseInt(charSequence.toString())));
                    carbohydrates.setText(String.valueOf(food.getCarbohydratesPerGrams(grams)));
                    protein.setText(String.valueOf(food.getProteinPerGrams(grams)));
                    fat.setText(String.valueOf(food.getFatPerGrams(grams)));
                    calories.setText(String.valueOf(food.getCarbohydratesPerGrams(grams)));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (gramInput.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Įveskite gramus!", Toast.LENGTH_SHORT).show();
                } else {
                    addLogItem(new LogItem(food, Integer.parseInt(gramInput.getText().toString()), new Date()), context);
                    ((ChooseFoodTabs) context).generateToast(gramInput.getText().toString() + "g. " + food.Name);
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }

    private static void addLogItem(LogItem item, Context context) {
        try {

            DatabaseHelper helper = getHelper(context);
            final Dao<LogItem, Integer> logDao = helper.getLogDao();

            //idedam i duombaze
            logDao.create(item);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper getHelper(Context context) {
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

}
