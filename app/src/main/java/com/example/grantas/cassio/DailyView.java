package com.example.grantas.cassio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.CreateFoodLogic;
import com.example.grantas.cassio.FragmentLogic.DailyViewLogic;
import com.example.grantas.cassio.Tools.DayExpandableListAdapter;
import com.example.grantas.cassio.Tools.DayItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DailyView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DailyView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyView extends Fragment {

    private OnFragmentInteractionListener mListener;
    private DailyViewLogic Logic;
    private View rootView;
    private LinearLayout linearLayout;

    public DailyView() {
        // Required empty public constructor
    }

    public static DailyView newInstance() {
        DailyView fragment = new DailyView();
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


        View listView = setListView(rootView);

        return listView;
    }

    public View setListView(View view) {
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.list_days);
        List<DayItem> days = Logic.getDays();

        DayExpandableListAdapter adapter = new DayExpandableListAdapter(getActivity(), days);
        listView.setAdapter(adapter);
        listView.setGroupIndicator(null);
        TextView empty = (TextView) rootView.findViewById(R.id.empty_day_view);
        listView.setEmptyView(empty);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {

                return true;
            }
        });
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.clear_final_button)
    public void clearDays() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.confirm_save_and_clear);

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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logic.onDestroy();
    }
}
