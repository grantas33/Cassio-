package com.cassio.app.cassio.models;

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
