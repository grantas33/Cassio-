package com.example.grantas.cassio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.grantas.cassio.FragmentLogic.CreateFoodLogic;
import com.example.grantas.cassio.FragmentLogic.DailyViewLogic;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;


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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_daily_view, container, false);

        ButterKnife.bind(this, rootView);
        Logic = new DailyViewLogic(getContext());

        //i cia desim savo LisView
        linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout);
        ListView dynamicListView = new ListView(getContext());

        //final raktazodis nebutinas, jis tiesiog neleidzia priskirti naujos reiksmes (kaip read only)
        final List<String> foods = Logic.getFoodStrings();

        //sukuriam adapteri. gali buti custom adapteris. atkreipti demesi i ANDROID.R.layout.simple_list_item_1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, foods);

        dynamicListView.setAdapter(adapter);

        linearLayout.addView(dynamicListView);
        return rootView;
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
