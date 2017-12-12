package com.cassio.app.cassio;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cassio.app.cassio.Tools.AlarmReceiver;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_AUTOSAVE = "pref_autosave";
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();
        getSupportActionBar().setTitle(getString(R.string.action_settings));

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.OnSharedPreferenceChangeListener listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                        if (key.equals(KEY_PREF_AUTOSAVE)) {
                            checkAlarm(sharedPref, SettingsActivity.this);
                        }
                    }
                };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    public void checkAlarm(SharedPreferences sharedPref, Context context) {
        boolean autoSavePref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_AUTOSAVE, true);
        if (autoSavePref) {
            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            // Set the alarm to start at approximately 0:00 p.m.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Intent intent = new Intent(context, AlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmIntent);

            Log.i("SETTINGS", "pasetinta");
        } else {
            if (alarmIntent != null) {
                alarmMgr.cancel(alarmIntent);
            }
            Log.i("SETTINGS", "cancelino");
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }

    }
}
