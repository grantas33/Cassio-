package com.cassio.app.cassio.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cassio.app.cassio.SettingsActivity;


/**
 * Klase is naujo uzdedanti aliarmus kai telefonas persikrauna
 */

public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SettingsActivity instance = new SettingsActivity();
        instance.checkAlarm(pref, context);
    }
}
