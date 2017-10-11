package com.cassio.app.cassio.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cassio.app.cassio.ChooseFoodTabs;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.fragmentLogic.ChooseFoodLogic;
import com.cassio.app.cassio.models.LogItem;
import com.cassio.app.cassio.R;
import com.cassio.app.cassio.adapters.FoodExpandableListAdapter;

import java.util.Date;

public class AdvancedUserfoodAddDialog {
    public static void createSelectAmountAlertDialog(final Activity Context, final Food item, final ChooseFoodLogic Logic, final FoodExpandableListAdapter adapter) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        // builder.setView(R.layout.select_amount_dialog);

        LayoutInflater inflater = Context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.select_amount_dialog, null);
        builder.setView(dialogView);

        EditText graminput = (EditText) dialogView.findViewById(R.id.select_dialog_graminput);
        final CheckBox remember = (CheckBox) dialogView.findViewById(R.id.select_dialog_remember);
        final TextView grams = (TextView) dialogView.findViewById(R.id.select_dialog_grams);
        final TextView calories = (TextView) dialogView.findViewById(R.id.select_dialog_calories);

        grams.setText(String.valueOf(item.Grams));
        calories.setText(String.valueOf(item.Calories));
        graminput.append(String.valueOf(item.Grams));

        graminput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    grams.setText(String.valueOf(item.Grams));
                    calories.setText(String.valueOf(item.Calories));
                } else {
                    grams.setText(s);
                    calories.setText(String.valueOf(Math.round(((double) Integer.parseInt(s.toString()) / (double) item.Grams) * (double) item.Calories)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setPositiveButton(
                Context.getString(R.string.add),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Logic.AddLogItem(new LogItem(item, Integer.parseInt(grams.getText().toString()), new Date()));
                        ((ChooseFoodTabs) Context).generateToast(grams.getText() + "g. " + item.Name);
//                        if(mToast != null) mToast.cancel();
//                        mToast = Toast.makeText(Context, "Pridėta " + grams.getText() + "g " + item.Name, Toast.LENGTH_SHORT);
//                        mToast.show();
                        if (remember.isChecked()) {
                            Logic.UpdateGramsFoodItem(Integer.parseInt(grams.getText().toString()), item);
                            adapter.UpdateAdapter(Logic.getSortedFoods());
                        }
                    }
                }
        );

        builder.setNegativeButton(
                R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
