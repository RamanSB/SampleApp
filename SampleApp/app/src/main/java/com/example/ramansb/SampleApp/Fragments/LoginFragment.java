package com.example.ramansb.caco.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 07/08/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener{

    public static final String DATA_TAG = LoginFragment.class.getSimpleName();

    private TextView loginTextView;
    private TextView forgotPasswordTextView;
    private TextInputEditText usernameInputEditText;
    private TextInputEditText passwordInputEditText;
    private TextInputLayout usernameTextLayout;
    private TextInputLayout passwordTextLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login_upper, null);
        initViews(rootView);
        setTextViewTypefaceAndFonts();
       /*
        if(savedInstanceState!=null){
            loginTextView.setText(savedInstanceState.get("Username").toString());
        }
        */
       forgotPasswordTextView.setClickable(true);
        return rootView;

    }


    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(DATA_TAG, "on Saved Instance State called");
        outState.putString("Username", usernameInputEditText.getText().toString());
    }
    */

    private void initViews(View view) {
        loginTextView = (TextView) view.findViewById(R.id.tv_login_text);
        forgotPasswordTextView = (TextView) view.findViewById(R.id.tv_forgot_password);
        usernameInputEditText = (TextInputEditText) view.findViewById(R.id.tiet_login_username);
        passwordInputEditText = (TextInputEditText) view.findViewById(R.id.tiet_login_password);
        usernameTextLayout = (TextInputLayout) view.findViewById(R.id.til_login_username);
        passwordTextLayout = (TextInputLayout) view.findViewById(R.id.til_login_password);

    }


    private void setTextViewTypefaceAndFonts() {
        Typeface faceChocolateAndDelight = Typeface.createFromAsset(getContext().getAssets(), "font/ChocolateAndDelight.ttf");
        Typeface faceCinematograficaRegular = Typeface.createFromAsset(getContext().getAssets(), "font/Cinematografica-Regular-trial.ttf");
        loginTextView.setTextSize(120);
        loginTextView.setTypeface(faceChocolateAndDelight);
    }

    private void initListeners(){
      forgotPasswordTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewClicked = view.getId();

        switch(viewClicked){

            case R.id.tv_forgot_password:
                //show Forgot password dialog
                break;

        }
    }


}