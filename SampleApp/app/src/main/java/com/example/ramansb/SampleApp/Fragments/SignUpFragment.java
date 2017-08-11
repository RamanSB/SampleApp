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

public class SignUpFragment extends Fragment {

    private TextView signUpText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_sign_up, null);
        initViews(rootView);
        setTextViewTypefaceAndFonts();
        return rootView;

    }

    private void initViews(View view){
            signUpText = (TextView) view.findViewById(R.id.tv_signup);
    }

    private void setTextViewTypefaceAndFonts() {
        Typeface faceChocolateAndDelight = Typeface.createFromAsset(getContext().getAssets(), "font/ChocolateAndDelight.ttf");

        signUpText.setTypeface(faceChocolateAndDelight);
        signUpText.setTextSize(80);


    }
}
