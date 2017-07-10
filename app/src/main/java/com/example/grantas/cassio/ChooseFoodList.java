package com.example.grantas.cassio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.ChooseFoodLogic;
import com.example.grantas.cassio.FragmentLogic.DailyViewLogic;
import com.example.grantas.cassio.Tools.FoodExpandableListAdapter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Grantas on 2017-07-09.
 */

public class ChooseFoodList extends Fragment {
    private Toast mToast = null;
    private String toastMsg = "";
    private int counter = 1;
    ExpandableListView mExpanded;
    SearchView mSearchView;

    public ChooseFoodList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_food_expandablelist, container, false);
        ChooseFoodLogic Logic = new ChooseFoodLogic(getContext());

        mExpanded = (ExpandableListView) view.findViewById(R.id.expandableviewmyfoods);
        mSearchView = (SearchView) view.findViewById(R.id.searchviewmyfoods);
        TextView mEmptyView = (TextView) view.findViewById(R.id.emptymyfoodsview);
        List<Food> templist = Logic.getSortedFoods();
        ExpandableListAdapter adapter = new FoodExpandableListAdapter(getActivity(), templist);
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


//        mExpanded.GroupClick += (object sender, ExpandableListView.GroupClickEventArgs e) =>
//        {
//            ImageView plussign = e.ClickedView.FindViewById<ImageView>(Resource.Id.greenplus);                               //animacija
//            Android.Views.Animations.AlphaAnimation plusClick = new Android.Views.Animations.AlphaAnimation(1F, 0.8F);
//            plusClick.Duration = 150;
//            plussign.StartAnimation(plusClick);
//
//            Food food = new Food(templist[e.GroupPosition]);
//            MainActivity.foodsdb.AddData(food);
//            MainActivity.NutritionEdit.PutString("cal", (int.Parse(MainActivity.localNutritionData.GetString("cal", "0")) + food.Calories).ToString());
//            MainActivity.NutritionEdit.Apply();
//
//            if (toastMsg == string.Format("Added {0} {1}", counter, MainActivity.foodsdb.GetLast().Name))
//            {
//                counter++;
//            }
//            else
//            {
//                counter = 1;
//            }
//
//
//            toastMsg = string.Format("Added {0} {1}", counter, MainActivity.foodsdb.GetLast().Name);
//            if (mToast != null) mToast.Cancel();
//            mToast = Toast.MakeText(view.Context, toastMsg, ToastLength.Short);
//            mToast.Show();
//
//        };

        return view;
    }
}