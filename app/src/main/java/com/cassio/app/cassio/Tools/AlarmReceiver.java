package com.cassio.app.cassio.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cassio.app.cassio.fragmentLogic.SaveAndClearLogic;


/**
 * Created by Grantas on 2017-07-20.
 * Klase daranti veiksmus kai ateina aliarmo data
 */

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
      //  Log.i("ALARM", "received");
        SaveAndClearLogic Logic = new SaveAndClearLogic(context);
        Logic.saveLogs(true);
        Logic.clearLogs();
    }
}
