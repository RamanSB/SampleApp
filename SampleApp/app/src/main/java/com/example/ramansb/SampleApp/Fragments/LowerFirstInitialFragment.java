package com.example.ramansb.caco.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 07/08/2017.
 */

public class LowerFirstInitialFragment extends Fragment {

    private TextView textViewThe;
    private TextView textViewHighLife;
    private TextView textViewExclusive;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_lower_first_initial, null);
        initViews(rootView);
        setTextViewTypefaceAndFonts();
        return rootView;
    }

    private void initViews(View view){
        textViewThe = (TextView) view.findViewById(R.id.tv_the);
        textViewHighLife = (TextView) view.findViewById(R.id.tv_highlife);
        textViewExclusive = (TextView) view.findViewById(R.id.tv_is_exclusive);
    }

    private void setTextViewTypefaceAndFonts() {
        Typeface faceChocolateAndDelight = Typeface.createFromAsset(getContext().getAssets(), "font/ChocolateAndDelight.ttf");
        Typeface faceCinematograficaRegular = Typeface.createFromAsset(getContext().getAssets(), "font/Cinematografica-Regular-trial.ttf");

        textViewHighLife.setTextSize(120);
        textViewThe.setTextSize(40);
        textViewExclusive.setTextSize(40);

        textViewHighLife.setTypeface(faceChocolateAndDelight);
        textViewThe.setTypeface(faceCinematograficaRegular);
        textViewExclusive.setTypeface(faceCinematograficaRegular);
    }
}
