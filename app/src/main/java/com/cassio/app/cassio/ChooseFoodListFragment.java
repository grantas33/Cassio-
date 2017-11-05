package com.cassio.app.cassio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.cassio.app.cassio.fragmentLogic.ChooseFoodLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.adapters.FoodExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseFoodListFragment extends Fragment {
    @BindView(R.id.expandableviewmyfoods)
    ExpandableListView mExpanded;
    @BindView(R.id.searchviewmyfoods)
    SearchView mSearchView;
    @BindView(R.id.emptymyfoodsbutton)
    Button mEmptyButton;
    public FoodExpandableListAdapter adapter;
    public static String foodNameExtra = null;


    public ChooseFoodListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_food_expandablelist, container, false);
        ButterKnife.bind(this, view);
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.clearFocus();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mSearchView.setQueryHint("Ieškoti produkto");
        LinearLayout mEmptyView = (LinearLayout) view.findViewById(R.id.emptymyfoodsview);
        ChooseFoodLogic Logic = new ChooseFoodLogic(getContext());
        final List<Food> datalist = Logic.getSortedFoods();
        adapter = new FoodExpandableListAdapter(getActivity(), datalist);
        mExpanded.setAdapter(adapter);
        mExpanded.setEmptyView(mEmptyView);
        mExpanded.setGroupIndicator(null);

//        Kad neexpandintusi paspaudus betkur
        mExpanded.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {
                return true;
            }
        });

        mExpanded.setOnItemLongClickListener(longClickListener);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Food> temp = new ArrayList<>();
                for (Food food : datalist) {
                    if (food.Name.toLowerCase().contains(newText.toLowerCase())) {
                        temp.add(food);
                    }
                }

                adapter.UpdateAdapter(temp);
                int count = adapter.getGroupCount();         //collapse all groups
                for (int i = 0; i < count; i++)
                    mExpanded.collapseGroup(i);

                return true;
            }
        });

        return view;
    }

    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
            Intent intent = new Intent(getContext(), AddToRecipeActivity.class);
            Food food = (Food) mExpanded.getItemAtPosition(pos);
            intent.putExtra("EXTRA_FOOD", food);
            startActivity(intent);
            return true;
        }
    };

    @OnClick(R.id.emptymyfoodsbutton)
    public void onClick(View v) {
        foodNameExtra = mSearchView.getQuery().toString();
        getActivity().onBackPressed();
    }
}


