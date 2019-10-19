package com.cassio.app.cassio.interfaces;

import com.cassio.app.cassio.models.LogItem;

import java.util.List;

public interface ILogProvider {
    List<LogItem> getAllLogs();
}
