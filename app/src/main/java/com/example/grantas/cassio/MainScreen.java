package com.example.grantas.cassio;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.MainScreenLogic;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreen extends Fragment {

    @BindView(R.id.calories_main)
    TextView Calories;

    @BindView(R.id.nutrition_chart)
    PieChart nutritionChart;

    MainScreenLogic Logic;

    public MainScreen() {
        // Required empty public constructor
    }

    public static MainScreen newInstance() {
        MainScreen fragment = new MainScreen();
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
//        //labels
         nutritionChart.setDrawEntryLabels(true);
//        xAxis.setXOffset(0f);
//        xAxis.setYOffset(0f);
//        xAxis.setTextSize(16f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                ArrayList<String> strings = getChartLabels();
//                String[] array = new String[strings.size()];
//                array = strings.toArray(array);
//                 return array[(int) value % array.length];
//            }
//        });

//        //values
//        YAxis yAxis = nutritionChart.getYAxis();
//        yAxis.setAxisMaximum(100f);
//        yAxis.setAxisMinimum(0f);
//        yAxis.setTextSize(8f);
//        yAxis.setLabelCount(3, false);
//        yAxis.setEnabled(false);
//
//
//        nutritionChart.getLegend().setEnabled(false);
//        nutritionChart.getLegend().setTextSize(0f);
//        nutritionChart.getDescription().setEnabled(false);
//        nutritionChart.animateXY(
//                1400, 1400,
//                Easing.EasingOption.EaseInOutQuad,
//                Easing.EasingOption.EaseInOutQuad);
//
//
//        dataSets.add(getDataSet());
//        RadarData data = new RadarData(dataSets);
//        data.setValueTextSize(8f);
//
//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return String.valueOf((int) value);
//            }
//
//        });
//        nutritionChart.setData(data);
//        nutritionChart.invalidate();
        nutritionChart.setUsePercentValues(true);
        Description description = new Description();
        description.setText("Maistingumas");
        nutritionChart.setDescription(description);

        nutritionChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;

//                Toast.makeText(MainActivity.this,
//                        xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        setChartData();
    }

    public ArrayList<String> getChartLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add(getString(R.string.carbohydrates));
        labels.add(getString(R.string.protein));
        labels.add(getString(R.string.fat));
        return labels;
    }

    public void setChartData() {
        ArrayList<PieEntry> nutrition = new ArrayList<>();
        float carbohydrates = (float) Logic.getTotalCarbohydrates();
        float protein = (float) Logic.getTotalProtein();
        float fat = (float) Logic.getTotalFat();
        nutrition.add(new PieEntry(carbohydrates, 0));
        nutrition.add(new PieEntry(protein, 1));
        nutrition.add(new PieEntry(fat, 2));

        PieDataSet dataSet = new PieDataSet(nutrition, "Dabartinis maistingumas");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);

        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface();
        nutritionChart.setData(data);

        // undo all highlights
        nutritionChart.highlightValues(null);//        dataSet.setColor(R.color.colorPrimary);
//        dataSet.setDrawFilled(true);
        nutritionChart.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        int calories = Logic.getTotalCalories();
        try {
            Calories.setText(Integer.toString(calories));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
