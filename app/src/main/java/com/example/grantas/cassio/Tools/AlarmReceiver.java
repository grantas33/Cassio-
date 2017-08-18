package com.example.grantas.cassio.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.FoodLogLogic;
import com.example.grantas.cassio.FragmentLogic.SaveAndClearLogic;
import com.example.grantas.cassio.MainActivity;


/**
 * Created by Grantas on 2017-07-20.
 * Klase daranti veiksmus kai ateina aliarmo data
 */

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ALARM", "received");
        SaveAndClearLogic Logic = new SaveAndClearLogic(context);
        Logic.saveLogs(true);
        Logic.clearLogs();
    }
}
