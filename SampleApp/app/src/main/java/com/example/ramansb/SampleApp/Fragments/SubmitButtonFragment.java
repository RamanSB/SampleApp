package com.example.ramansb.caco.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ramansb.caco.MainActivity;
import com.example.ramansb.caco.R;

/**
 * Created by RamanSB on 07/08/2017.
 */

public class SubmitButtonFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = SubmitButtonFragment.class.getSimpleName();

    OnSubmitClickListener mCallback;

    private Button submitButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_submit_button, null);
        initViews(rootView);
        initListeners();
        return rootView;
    }

    private void initViews(View view) {
        submitButton = (Button) view.findViewById(R.id.btn_submit);
    }

    private void initListeners() {
        submitButton.setOnClickListener(this);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                mCallback = (OnSubmitClickListener) activity;
            } catch (ClassCastException ex) {
                throw new ClassCastException("The activity did not implement this fragments interface");
            }
        }
    }


    public interface OnSubmitClickListener {
        public void onSubmit();
    }

    @Override
    public void onClick(View view) {

        int viewClickedId = view.getId();
        Log.v(TAG, "Submit clicked");
        switch (viewClickedId) {

            case R.id.btn_submit:
                if (MainActivity.isHomeScreenDisplayed) {
                    view.setClickable(false);
                } else {
                    view.setClickable(true);
                    mCallback.onSubmit();
                }
        }

    }
}
