package com.cassio.app.cassio.dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cassio.app.cassio.FragmentLogic.SaveAndClearLogic;
import com.cassio.app.cassio.MainActivity;
import com.cassio.app.cassio.R;
import com.cassio.app.cassio.SettingsActivity;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class SaveAndClearDialog {
    MainActivity context;
    SaveAndClearLogic Logic;

    public SaveAndClearDialog(MainActivity context) {
        this.context = context;
        Logic = new SaveAndClearLogic(context);
    }


    public void createAlertDialog() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        boolean autoSavePref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_AUTOSAVE, true);
        if (autoSavePref) {
            SuperActivityToast.create(context, new Style(), Style.TYPE_STANDARD)
                    .setFrame(Style.FRAME_STANDARD)
                    .setDuration(Style.DURATION_LONG)
                    .setText(context.getString(R.string.autosave_information_alert))
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED)).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.confirm_save_and_clear);

            builder.setPositiveButton(
                    context.getString(R.string.yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Logic.saveLogs(false);
                            Logic.clearLogs();
                            dialog.cancel();
                            context.displayView(context.currentViewId);
                        }
                    }
            );

            builder.setNegativeButton(
                    R.string.no,
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

}
