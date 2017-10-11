package com.cassio.app.cassio.tools;

import com.cassio.app.cassio.Food;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Grantas on 2017-08-20.
 */

public class FoodValueFormatter implements IValueFormatter {
    private DecimalFormat mFormat;
    public Food item;


    public FoodValueFormatter(Food item) {
        this.item = item;
        mFormat = new DecimalFormat("########0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        //return mFormat.format(value*((float)item.Grams)/(float)100) + "g";
        if(value == 0.00001f) return "";
        else return mFormat.format(value) + "g";


    }
}
