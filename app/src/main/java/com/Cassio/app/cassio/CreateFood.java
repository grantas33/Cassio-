package com.Cassio.app.cassio;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Cassio.app.cassio.FragmentLogic.CreateFoodLogic;
import com.Cassio.app.cassio.Tools.InvalidValueException;
import com.google.zxing.integration.android.IntentIntegrator;

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

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;       // kameros prieigai

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
    private String toast;

    private OnFragmentInteractionListener mListener;

    public CreateFood() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.scan)
    public void scan(View view) {


        String[] permissions = new String[] {Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            this.requestPermissions(permissions, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else
        {
            MainActivity activity = (MainActivity) getActivity();
            activity.scanBarcode(view);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {                      //jei pasirenkam "ALLOW"
                        scan(this.getView());
                    } else {                                                                        //jei pasirenkam "DENY"
                        Toast.makeText(this.getContext(), R.string.camera_permission_denied, Toast.LENGTH_LONG).show();
                    }
                }

    public void myClickHandler(View target) {
        // Do stuff
        IntentIntegrator.forSupportFragment(this).initiateScan();
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
            Logic.GenerateToast("Produktas " + name + " sukurtas!");
            resetForm();
        } catch (InvalidValueException e) {
            Logic.GenerateToast(e.getMessage());
        } catch (Exception e) {
            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void resetForm() {
        Name.setText("");
        Name.setEnabled(true);
        Grams.setText("");
        Grams.setEnabled(true);
        Carbohydrates.setText("");
        Carbohydrates.setEnabled(true);
        Protein.setText("");
        Protein.setEnabled(true);
        Fat.setText("");
        Fat.setEnabled(true);
        Calories.setText("");
        Calories.setEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_food, container, false);
        ButterKnife.bind(this, view);
        if(ChooseFoodList.foodNameExtra != null)
        {
            Name.setText(ChooseFoodList.foodNameExtra);
            ChooseFoodList.foodNameExtra = null;
        }
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