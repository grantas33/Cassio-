package com.cassio.app.cassio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateRecipeFragment extends Fragment {

    @BindView(R.id.recipe_list)
    ListView RecipeList;
    @BindView(R.id.create_recipe_confirm)
    Button ConfirmButton;
    @BindView(R.id.total_carbohydrates)
    TextView Carbohydrates;
    @BindView(R.id.total_protein)
    TextView Protein;
    @BindView(R.id.total_fat)
    TextView Fat;
    @BindView(R.id.recipe_name)
    EditText RecipeName;

    public CreateRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_recipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
