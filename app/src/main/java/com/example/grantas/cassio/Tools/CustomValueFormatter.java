package com.example.grantas.cassio.Tools;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Grantas on 2017-08-18.
 */

public class CustomValueFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    public CustomValueFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        if(value<10.0f) {
            return "";
        }
        else{ return mFormat.format(value) + "%"; }

    }
}