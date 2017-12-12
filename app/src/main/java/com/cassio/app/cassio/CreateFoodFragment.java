package com.cassio.app.cassio;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cassio.app.cassio.FragmentLogic.CreateFoodLogic;
import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.Tools.InvalidValueException;
import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Integer.parseInt;

public class CreateFoodFragment extends Fragment {

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

    public CreateFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.scan)
    public void scan(View view) {


        String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(permissions, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
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
    public void submit() {
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
        if (ChooseFoodListFragment.foodNameExtra != null) {
            Name.setText(ChooseFoodListFragment.foodNameExtra);
            ChooseFoodListFragment.foodNameExtra = null;
        }
        Logic = new CreateFoodLogic(getContext());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    public void processScan(Food result) {
        if (result.Name != "notset") {

            Name.setText(result.Name);
            Name.setEnabled(false);
            Calories.setText(String.valueOf(result.getCalories()));
            Calories.setEnabled(false);
            Grams.setText(String.valueOf(result.Grams));
            Carbohydrates.setText(String.valueOf(result.getCarbohydrates()));
            Carbohydrates.setEnabled(false);
            Protein.setText(String.valueOf(result.getProtein()));
            Protein.setEnabled(false);
            Fat.setText(String.valueOf(result.getFat()));
            Fat.setEnabled(false);

            Grams.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int grams;
                    if (!Grams.getText().toString().equals("")) {
                        grams = parseInt(Grams.getText().toString());
                    } else {
                        grams = 100;
                    }

                    Calories.setText(String.valueOf(result.getCaloriesPerGrams(grams)));
                    Carbohydrates.setText(String.valueOf(result.getCarbohydratesPerGrams(grams)));
                    Protein.setText(String.valueOf(result.getProteinPerGrams(grams)));
                    Fat.setText(String.valueOf(result.getFatPerGrams(grams)));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            Toast.makeText(getContext(), "Nerasta!", Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
