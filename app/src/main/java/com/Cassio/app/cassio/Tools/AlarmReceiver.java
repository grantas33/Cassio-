package com.Cassio.app.cassio.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Cassio.app.cassio.FragmentLogic.SaveAndClearLogic;


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
