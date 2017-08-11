package com.example.ramansb.caco.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 07/08/2017.
 */

public class UpperInitialFragment extends Fragment {


    private TextView textViewKnowing;
    private TextView textViewBeautiful;
    private TextView textViewHighFasion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upper_initial, null);
        initViews(rootView);
        setTextViewTypefaceAndFonts();
        return rootView;
    }

    private void initViews(View view) {

        textViewKnowing = (TextView) view.findViewById(R.id.tv_knowing);
        textViewBeautiful = (TextView) view.findViewById(R.id.tv_is_beautiful);
        textViewHighFasion = (TextView) view.findViewById(R.id.tv_highfasion);
    }

    private void setTextViewTypefaceAndFonts() {
        Typeface faceChocolateAndDelight = Typeface.createFromAsset(getContext().getAssets(), "font/ChocolateAndDelight.ttf");
        Typeface faceCinematograficaLight = Typeface.createFromAsset(getContext().getAssets(), "font/Cinematografica-Light-trial.ttf");
        Typeface faceCinematograficaRegular = Typeface.createFromAsset(getContext().getAssets(), "font/Cinematografica-Regular-trial.ttf");
        textViewKnowing.setTextSize(220);
        textViewBeautiful.setTextSize(120);
        textViewHighFasion.setTextSize(70);

        textViewKnowing.setTypeface(faceChocolateAndDelight);
        textViewBeautiful.setTypeface(faceChocolateAndDelight);
        textViewHighFasion.setTypeface(faceCinematograficaLight);

    }
}
