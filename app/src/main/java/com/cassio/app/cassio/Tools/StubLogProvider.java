package com.cassio.app.cassio.Tools;

import com.cassio.app.cassio.interfaces.ILogProvider;
import com.cassio.app.cassio.models.LogItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StubLogProvider implements ILogProvider {
    @Override
    public List<LogItem> getAllLogs() {
        List<LogItem> items = new ArrayList<>();
        items.add(new LogItem(DefaultFood.DataArrays[0][0], 10, new Date()));
        items.add(new LogItem(DefaultFood.DataArrays[0][1], 10, new Date()));
        items.add(new LogItem(DefaultFood.DataArrays[0][3], 4, new Date()));
        items.add(new LogItem(DefaultFood.DataArrays[0][2], 10, new Date()));
        items.add(new LogItem(DefaultFood.DataArrays[0][5], 2, new Date()));
        items.add(new LogItem(DefaultFood.DataArrays[0][7], 700, new Date()));
        return items;
    }
}
