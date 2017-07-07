package com.example.grantas.cassio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.grantas.cassio.FragmentLogic.CreateFoodLogic;
import com.example.grantas.cassio.Tools.InvalidValueException;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFood.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the link CreateFoodnewInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFood extends Fragment {

    @BindView(R.id.name)
    EditText Name;
    @BindView(R.id.calories)
    EditText Calories;
    @BindView(R.id.grams)
    EditText Grams;
    @BindView(R.id.submit)
    Button Add;
    @BindView(R.id.scan)
    Button Scan;
    @BindView(R.id.carbohydrates)
    EditText Carbohydrates;
    @BindView(R.id.protein)
    EditText Protein;
    @BindView(R.id.fat)
    EditText Fat;

    CreateFoodLogic Logic; //loginis fragmento sluoksnis


    private OnFragmentInteractionListener mListener;

    public CreateFood() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.submit)
    public void Submit()
    {
        String calories;
        String grams;
        String carbohydrates;
        String protein;
        String fat;
        String name = Name.getText().toString();
        calories = Calories.getText().toString();
        grams = Grams.getText().toString();
        carbohydrates = Carbohydrates.getText().toString();
        protein = Protein.getText().toString();
        fat = Fat.getText().toString();

        try {
            Logic.Add(name, calories, grams, carbohydrates, protein, fat);
        } catch (InvalidValueException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        Toast.makeText(getContext(), name + " " + getString(R.string.adeed), Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_food, container, false);
        ButterKnife.bind(this, view);
        Logic = new CreateFoodLogic(getContext());
        return view;
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
    public void onDestroy() {
        super.onDestroy();
        Logic.onDestroy();
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
}
