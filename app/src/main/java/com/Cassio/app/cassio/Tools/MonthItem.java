package com.Cassio.app.cassio.Tools;

/**
 * Created by Lukas on 7/21/2017.
 */

public class MonthItem {
    public String Year;
    public String Month;
    public int Calories;

    public MonthItem(String year, String month) {
        Year = year;
        Month = month;
    }

    public void addCalories(int calories) {
        Calories += calories;
    }
}
