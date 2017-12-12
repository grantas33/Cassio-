package com.cassio.app.cassio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cassio.app.cassio.FragmentLogic.FoodLogLogic;
import com.cassio.app.cassio.adapters.LogItemListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodLogFragment extends Fragment {

    @BindView(R.id.foodloglistview)
    ListView mListView;
    @BindView(R.id.emptyfoodlogview)
    View emptyText;

    LogItemListAdapter adapter;
    FoodLogLogic Logic = new FoodLogLogic(getContext());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_log, container, false);
        ButterKnife.bind(this, view);
        mListView.setEmptyView(emptyText);
        adapter = new LogItemListAdapter(getContext(), Logic.getLogItems());
        mListView.setAdapter(adapter);

        return view;
    }
}
