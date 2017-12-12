package com.cassio.app.cassio;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cassio.app.cassio.FragmentLogic.MainScreenLogic;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainScreenFragment extends Fragment {

    @BindView(R.id.nutrition_chart)
    PieChart nutritionChart;

    MainScreenLogic Logic;

    public MainScreenFragment() {
        // Required empty public constructor
    }

    public static MainScreenFragment newInstance() {
        MainScreenFragment fragment = new MainScreenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        Logic = new MainScreenLogic(getContext());
        ButterKnife.bind(this, view);
        setNutritionChart();

        return view;
    }

    public void setNutritionChart() {
        //labels
        nutritionChart.setDrawEntryLabels(false);
        nutritionChart.setUsePercentValues(true);
        nutritionChart.setDescription(null);
        nutritionChart.setCenterText(generateCenterSpannableText());
        nutritionChart.setHoleRadius(58f);
        nutritionChart.setTransparentCircleRadius(61f);

        nutritionChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
            }

            @Override
            public void onNothingSelected() {
            }
        });
        setChartData();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString(String.valueOf(Logic.getTotalCalories()) + "\nkal.");
        s.setSpan(new RelativeSizeSpan(5.0f), 0, s.length() - 5, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, s.length() - 5, 0);
        s.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, s.length() - 5, 0);
        return s;
    }

    public void setChartData() {
        ArrayList<PieEntry> nutrition = new ArrayList<>();
        float carbohydrates = (float) Logic.getTotalCarbohydrates();
        float protein = (float) Logic.getTotalProtein();
        float fat = (float) Logic.getTotalFat();
        nutrition.add(new PieEntry(carbohydrates, "Angliavandeniai"));
        nutrition.add(new PieEntry(protein, "Baltymai"));
        nutrition.add(new PieEntry(fat, "Riebalai"));

        PieDataSet dataSet = new PieDataSet(nutrition, null);
        dataSet.setSliceSpace(2f);
        dataSet.setFormSize(15.0f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        dataSet.setColors(Color.rgb(139, 204, 40), Color.rgb(40, 139, 204), Color.rgb(204, 40, 139));
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.rgb(250, 250, 250));
        nutritionChart.setData(data);

        // undo all highlights
        nutritionChart.highlightValues(null);
        nutritionChart.invalidate();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
