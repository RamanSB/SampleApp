package com.example.ramansb.caco.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ramansb.caco.MainActivity;
import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 09/08/2017.
 */

public class LoginButtonFragment extends Fragment implements View.OnClickListener {

    private Button loginButton;
    private OnLoginButtonListener mCallback;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.fragment_login_button , null);
        initViews(rootView);
        initListeners();
        return rootView;

    }

    private void initViews(View view){
        loginButton = (Button) view.findViewById(R.id.login_button);
    }

    private void initListeners(){
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity activity;
        if(context instanceof Activity){
            activity = (Activity) context;
            try{
                mCallback = (OnLoginButtonListener) activity;
            }catch(ClassCastException exception){
                throw new ClassCastException("Main activity did not implement the fragments interface");
            }
        }

    }

    public void onClick(View view){
        int viewClicked = view.getId();
        switch(viewClicked){

            case R.id.login_button:
                if(MainActivity.isHomeScreenDisplayed){
                    view.setClickable(false);
                }
                else{
                    view.setClickable(true);
                    mCallback.onLoginClicked();
                }
                break;

            case R.id.tv_forgot_password:
                showForgotPasswordDialog();
                break;

        }
    }


    private void showForgotPasswordDialog(){

    }

    public interface OnLoginButtonListener{
        public void onLoginClicked();
    }



}
