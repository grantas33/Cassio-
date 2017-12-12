package com.cassio.app.cassio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cassio.app.cassio.FragmentLogic.ChooseFoodLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.LogItem;
import com.cassio.app.cassio.Tools.DefaultFood;
import com.cassio.app.cassio.dialogs.AdvancedFoodAddDialog;
import com.cassio.app.cassio.adapters.ImageAdapter;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseFoodGridFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.gridview)
    GridView gridView;
    private Toast mToast = null;
    ChooseFoodLogic Logic;
    int index;

    public ChooseFoodGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index", 0);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_food_grid, container, false);
        ButterKnife.bind(this, view);
        Logic = new ChooseFoodLogic(getContext());
        gridView.setAdapter(new ImageAdapter(view.getContext(), DefaultFood.LogoArrays[index]));


        gridView.setOnItemLongClickListener(onItemLongClickListener);
        gridView.setOnItemClickListener(onItemClickListener);

        return view;
    }

    private void clickAction(Food food) {
        LogItem item = new LogItem(food, food.Grams, new Date());
        Logic.addLogItem(item);
        if (mToast != null) mToast.cancel();
        ((ChooseFoodTabs) getContext()).generateToast(food.Name);
    }

    GridView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
            AdvancedFoodAddDialog.getDialog(getActivity(), DefaultFood.DataArrays[index][position]).show();
            return true;
        }
    };

    GridView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            clickAction(DefaultFood.DataArrays[index][position]);
        }
    };
}
