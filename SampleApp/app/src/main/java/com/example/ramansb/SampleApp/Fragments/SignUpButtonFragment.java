package com.example.ramansb.caco.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ramansb.caco.MainActivity;
import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 07/08/2017.
 */

public class SignUpButtonFragment extends Fragment implements View.OnClickListener {

    private Button signUpButton;
    private OnSignUpClickListener mCallback;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_button, null);
        initViews(rootView);
        initListeners();
        return rootView;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                mCallback = (OnSignUpClickListener) activity;
            } catch (ClassCastException exception) {
                throw new ClassCastException("Activity did not implement the fragments callback interface");
            }
        }
    }

    private void initViews(View view) {
        signUpButton = (Button) view.findViewById(R.id.btn_signup);
    }

    private void initListeners() {
        signUpButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int viewClicked = view.getId();

        switch (viewClicked) {

            case R.id.btn_signup:
                if(MainActivity.isHomeScreenDisplayed){
                    view.setClickable(false);
                }
                else{
                    view.setClickable(true);
                    mCallback.onSignUp();
                }

                break;
        }
    }

    public interface OnSignUpClickListener {
        public void onSignUp();
    }
}
