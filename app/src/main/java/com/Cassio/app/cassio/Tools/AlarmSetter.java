package com.Cassio.app.cassio.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.Cassio.app.cassio.SettingsActivity;


/**
 * Created by Grantas on 2017-07-20.
 * Klase is naujo uzdedanti aliarmus kai telefonas persikrauna
 */

public class AlarmSetter extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SettingsActivity instance = new SettingsActivity();
        instance.CheckAlarm(pref, context);
     //   Log.i("ALARMSETTER", "VEIKIA!");
    }
}
