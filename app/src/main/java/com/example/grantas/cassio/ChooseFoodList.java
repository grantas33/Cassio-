package com.example.grantas.cassio;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.ChooseFoodLogic;
import com.example.grantas.cassio.FragmentLogic.DailyViewLogic;
import com.example.grantas.cassio.Tools.FoodExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.internal.Utils;

/**
 * Created by Grantas on 2017-07-09.
 */

public class ChooseFoodList extends Fragment{
    private Toast mToast = null;
    private String toastMsg = "";
    private int counter = 1;
    ExpandableListView mExpanded;
    SearchView mSearchView;
    public FoodExpandableListAdapter adapter;
    public static String foodNameExtra = null;

    public ChooseFoodList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // What i have added is this
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_food_expandablelist, container, false);

        mExpanded = (ExpandableListView) view.findViewById(R.id.expandableviewmyfoods);
        mSearchView = (SearchView) view.findViewById(R.id.searchviewmyfoods);
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint("Ieškoti produkto");
        //mSearchView.clearFocus();
       // mSearchView.onActionViewExpanded();
        LinearLayout mEmptyView = (LinearLayout) view.findViewById(R.id.emptymyfoodsview);
        Button mEmptyButton = (Button) view.findViewById(R.id.emptymyfoodsbutton);
        ChooseFoodLogic Logic = new ChooseFoodLogic(getContext());
        final List<Food> datalist = Logic.getSortedFoods();
        adapter = new FoodExpandableListAdapter(getActivity(), datalist);
        mExpanded.setAdapter(adapter);
        mExpanded.setEmptyView(mEmptyView);
        mExpanded.setGroupIndicator(null);

        //Kad neexpandintusi paspaudus betkur
        mExpanded.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {

                return true;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Food> temp = new ArrayList<Food>();
                for(Food food : datalist)
                {
                  if(food.Name.toLowerCase().contains(newText.toLowerCase()))
                  {
                      temp.add(food);
                  }
                }
                adapter.UpdateAdapter(temp);

                return true;
            }
        });

        mEmptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodNameExtra = mSearchView.getQuery().toString();
                getActivity().onBackPressed();
            }
        });


        return view;
    }

}


