package com.cassio.app.cassio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cassio.app.cassio.fragmentLogic.FoodLogLogic;
import com.cassio.app.cassio.tools.LogItemListAdapter;

/**
 * Created by Grantas on 2017-07-16.
 */

public class FoodLog extends Fragment {

    ListView mListView;
    LogItemListAdapter adapter;
    FoodLogLogic Logic = new FoodLogLogic(getContext());

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_log, container, false);
        mListView = (ListView) view.findViewById(R.id.foodloglistview);
        View emptytext = view.findViewById(R.id.emptyfoodlogview);
        mListView.setEmptyView(emptytext);
        adapter = new LogItemListAdapter(getContext(), Logic.getLogItems());
        mListView.setAdapter(adapter);


        return view;
    }
}
