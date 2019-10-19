package com.cassio.app.cassio.FragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.Tools.LogProvider;
import com.cassio.app.cassio.Tools.StubLogProvider;
import com.cassio.app.cassio.interfaces.ILogProvider;
import com.cassio.app.cassio.models.LogItem;

import java.util.List;

public class MainScreenLogic {

    private Context context;
    private ILogProvider logProvider;

    public MainScreenLogic(Context context, ILogProvider logProvider) {
        this.context = context;
        this.logProvider = logProvider;
    }

    public int getTotalCalories() {
        int total = 0;
        List<LogItem> items = logProvider.getAllLogs();
        for (LogItem item : items) {
            total += item.getCalories();
        }
        return total;
    }

    public double getTotalCarbohydrates() {
        double total = 0;
        List<LogItem> items = logProvider.getAllLogs();
        for (LogItem item : items) {
            total += item.getCarbohydrates();
        }
        return total;
    }

    public double getTotalProtein() {
        double total = 0;
        List<LogItem> items = logProvider.getAllLogs();
        for (LogItem item : items) {
            total += item.getProtein();
        }
        return total;
    }

    public double getTotalFat() {
        double total = 0;
        List<LogItem> items = logProvider.getAllLogs();
        for (LogItem item : items) {
            total += item.getFat();
        }
        return total;
    }
}
