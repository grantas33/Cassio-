package com.cassio.app.cassio.interfaces;

public interface AsyncTaskCompleteListener<T> {
    public void onTaskComplete(T result);
}
