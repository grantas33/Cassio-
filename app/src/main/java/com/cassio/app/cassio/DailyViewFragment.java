package com.cassio.app.cassio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cassio.app.cassio.FragmentLogic.DailyViewLogic;
import com.cassio.app.cassio.adapters.DayExpandableListAdapter;
import com.cassio.app.cassio.models.DayItem;
import com.cassio.app.cassio.Tools.DayItemsDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailyViewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private DailyViewLogic Logic;
    private View rootView;

    @BindView(R.id.list_days)
    ExpandableListView listView;
    @BindView(R.id.empty_day_view)
    TextView empty;

    ExpandableListAdapter adapter;
    List<String> groups;
    HashMap<String, List<DayItem>> groupDetail;

    public DailyViewFragment() {
        // Required empty public constructor
    }

    public static DailyViewFragment newInstance() {
        DailyViewFragment fragment = new DailyViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logic = new DailyViewLogic(getContext());
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_daily_view, container, false);
        ButterKnife.bind(this, rootView);
        setListView(rootView);

        return rootView;
    }

    public void setListView(final View view) {
        List<DayItem> days = Logic.getDays();
        groupDetail = DayItemsDataPump.getDataFromList(days);
        groups = new ArrayList<>(groupDetail.keySet());

        final DayExpandableListAdapter adapter = new DayExpandableListAdapter(getActivity(), groups, groupDetail);
        listView.setAdapter(adapter);
        listView.setGroupIndicator(null);
        listView.setEmptyView(empty);

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @BindView(R.id.expand_month)
            ImageView expandMonth;

            @Override
            public void onGroupExpand(int groupPosition) {
                expandMonth.setImageResource(R.drawable.ic_expand_less_black_48dp);
            }
        });

        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @BindView(R.id.expand_month)
            ImageView expandMonth;

            @Override
            public void onGroupCollapse(int groupPosition) {
                expandMonth.setImageResource(R.drawable.ic_expand_more_black_48dp);
            }
        });
        ButterKnife.bind(this, rootView);
    }

    @OnClick(R.id.clear_final_button)
    public void clearDays() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.confirm_clear_all);

        builder.setPositiveButton(
                getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Logic.clearDays();
                        dialog.cancel();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.displayView(mainActivity.currentViewId);
                    }
                }
        );

        builder.setNegativeButton(
                R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logic.onDestroy();
    }
}
