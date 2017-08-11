package com.example.ramansb.caco;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramansb.caco.Fragments.LoginButtonFragment;
import com.example.ramansb.caco.Fragments.LoginFragment;
import com.example.ramansb.caco.Fragments.LowerFirstInitialFragment;
import com.example.ramansb.caco.Fragments.LowerSecondInitialFragment;
import com.example.ramansb.caco.Fragments.SignUpButtonFragment;
import com.example.ramansb.caco.Fragments.SignUpFragment;
import com.example.ramansb.caco.Fragments.SubmitButtonFragment;
import com.example.ramansb.caco.Fragments.UpperInitialFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SignUpButtonFragment.OnSignUpClickListener, SubmitButtonFragment.OnSubmitClickListener, LoginButtonFragment.OnLoginButtonListener {

    public static final String MAIN_TAG = MainActivity.class.getSimpleName();

    private ConstraintLayout rootConstraintLayout;
    private ImageView imageViewMenuButton;
    private TextView textViewFirstCInLogo;
    private TextView textViewSecondCInLogo;

    public static int SDK;
    public static boolean isHomeScreenDisplayed = true;
    public static boolean isLoginDisplayed = false;
    public static boolean isSignUpDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);
        SDK = Build.VERSION.SDK_INT;

        initViews();
        setTextViewTypeFaceAndFonts();
        initListeners();
        initObjects();

    }

    @Override
    protected void onResume(){
        super.onResume();
        animateSquares();
    }


    private void initViews() {
        imageViewMenuButton = (ImageView) findViewById(R.id.ib_menu);
        rootConstraintLayout = (ConstraintLayout) findViewById(R.id.root_constraint_layout);
        textViewFirstCInLogo = (TextView) findViewById(R.id.tv_first_c_logo);
        textViewSecondCInLogo = (TextView) findViewById(R.id.tv_second_c_logo);
    }


    private void initListeners() {
        imageViewMenuButton.setOnClickListener(this);
    }

    private void initObjects() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ib_menu:
                animateMenuIcon();
                break;

        }
    }

    private void animateMenuIcon() {
        hideKeyboard();


        ValueAnimator fadeOutAnimation = ObjectAnimator.ofFloat(imageViewMenuButton, "alpha", 1f, 0f);
        fadeOutAnimation.setDuration(400);
        ValueAnimator fadeInAnimation = ObjectAnimator.ofFloat(imageViewMenuButton, "alpha", 0f, 1f);
        fadeInAnimation.setDuration(300);

        fadeInAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (!isHomeScreenDisplayed) {
                    imageViewMenuButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_close_80dp));
                } else {
                    imageViewMenuButton.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_menu_80dp));
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.v(MAIN_TAG, "Is Login Displayed:" + isLoginDisplayed);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

        });

        fadeOutAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (isHomeScreenDisplayed) {
                    displayLogInScreen();
                }else{
                    displayHomeScreen();
                }

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                imageViewMenuButton.setBackground(null);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(fadeOutAnimation).before(fadeInAnimation);
        animatorSet.start();

    }


    private void displayLogInScreen() {
        isSignUpDisplayed = false;
        isHomeScreenDisplayed = false;
        FragmentTransaction upperTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerPrimaryTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerSecondaryTransaction = getSupportFragmentManager().beginTransaction();
        upperTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        lowerPrimaryTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        lowerSecondaryTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

        upperTransaction.replace(R.id.fl_upper_frag_container, new LoginFragment());
        lowerPrimaryTransaction.add(R.id.fl_lower_first_frag_container, new SubmitButtonFragment());
        lowerSecondaryTransaction.add(R.id.fl_lower_second_frag_container, new SignUpButtonFragment());

        upperTransaction.commit();
        lowerPrimaryTransaction.commit();
        lowerSecondaryTransaction.commit();
        isLoginDisplayed = true;
        Log.v(MAIN_TAG, "Is Sign Up Displayed:" + isSignUpDisplayed);
        Log.v(MAIN_TAG, "Is Login Displayed:" + isLoginDisplayed);
        Log.v(MAIN_TAG, "Is Home Screen displayed:" + isHomeScreenDisplayed);

    }

    private void displaySignUpScreen() {
        isLoginDisplayed = false;
        isHomeScreenDisplayed = false;
        FragmentTransaction upperTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerPrimaryTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerSecondaryTransaction = getSupportFragmentManager().beginTransaction();
        upperTransaction.setCustomAnimations(R.anim.enter_from_above, R.anim.exit_to_left);
        lowerSecondaryTransaction.setCustomAnimations(R.anim.enter_from_below, R.anim.exit_to_left);
        lowerSecondaryTransaction.add(R.id.fl_lower_second_frag_container, new LoginButtonFragment());
        upperTransaction.replace(R.id.fl_upper_frag_container, new SignUpFragment());
        upperTransaction.commit();
        lowerSecondaryTransaction.commit();
        isSignUpDisplayed = true;
        Log.v(MAIN_TAG, "Is Sign Up Displayed:" + isSignUpDisplayed);
        Log.v(MAIN_TAG, "Is Login Displayed:" + isLoginDisplayed);

    }

    private void displayHomeScreen() {
        FragmentTransaction upperTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerPrimaryTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction lowerSecondaryTransaction = getSupportFragmentManager().beginTransaction();
        upperTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        lowerPrimaryTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        lowerSecondaryTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        upperTransaction.add(R.id.fl_upper_frag_container, new UpperInitialFragment());
        lowerPrimaryTransaction.add(R.id.fl_lower_first_frag_container, new LowerFirstInitialFragment());
        lowerSecondaryTransaction.add(R.id.fl_lower_second_frag_container, new LowerSecondInitialFragment());
        upperTransaction.commit();
        lowerPrimaryTransaction.commit();
        lowerSecondaryTransaction.commit();

        isHomeScreenDisplayed = true;
        isSignUpDisplayed = false;
        isLoginDisplayed = false;
        Log.v(MAIN_TAG, "Is Sign Up Displayed:" + isSignUpDisplayed);
        Log.v(MAIN_TAG, "Is Login Displayed:" + isLoginDisplayed);
    }


    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSubmit() {
        //If SignUpScreen Displayed
        //Validate Input Method InputValidation.validateInputs()...
        //Add all fields to database
        //Animate the topsquares colorwise if registration successful
        //Display Snackbar indicating successful registration
        //else if LoginScreenDisplayed then attempt login (Validate first)
        if (isSignUpDisplayed) {
            displaySnackbar();
            showRegistrationRequestSentDialog();
            displayLogInScreen();

            if (SDK >= Build.VERSION_CODES.LOLLIPOP) {
                animateSquares();
            }

        } else {
            /*Attempt to Log in*/
            Toast.makeText(this, "Login attempted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginClicked(){
        displayLogInScreen();
    }

    @Override
    public void onSignUp() {

        displaySignUpScreen();


    }

    private void displaySnackbar() {
        Snackbar.make(rootConstraintLayout, "Registration Request Sent", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", this).show();

    }


    private void showRegistrationRequestSentDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, 0);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Registration Request");
        alertDialogBuilder.setMessage(getString(R.string.text_alert_dialog_sign_up_request));
        alertDialogBuilder.setPositiveButton("Ok", null);
        final AlertDialog registrationRequestDialog = alertDialogBuilder.create();
        registrationRequestDialog.show();
    }



    private void setTextViewTypeFaceAndFonts(){
        Typeface faceChocolateAndDelight = Typeface.createFromAsset(getAssets(), "font/ChocolateAndDelight.ttf");
        Typeface faceCinematograficaHeavy = Typeface.createFromAsset(getAssets(), "font/Cinematografica-Regular-trial.ttf");

        textViewFirstCInLogo.setTypeface(faceCinematograficaHeavy);
        textViewSecondCInLogo.setTypeface(faceChocolateAndDelight);
        textViewFirstCInLogo.setTextSize(80);
        textViewSecondCInLogo.setTextSize(80);
    }





    private void animateSquares() {

        RelativeLayout sqOne, sqTwo, sqThree, sqFour, sqFive;
        sqOne = (RelativeLayout) findViewById(R.id.sq_1);
        sqTwo = (RelativeLayout) findViewById(R.id.sq_2);
        sqThree = (RelativeLayout) findViewById(R.id.sq_3);
        sqFour = (RelativeLayout) findViewById(R.id.sq_4);
        sqFive = (RelativeLayout) findViewById(R.id.sq_5);

        int colorSqOne = Color.argb(255, 205, 203, 243);
        int colorSqTwo = Color.argb(255, 188, 186, 225);
        int colorSqThree = Color.argb(255, 182, 182, 214);
        int colorSqFour = Color.argb(255, 172, 171, 203);
        int colorSqFive = Color.argb(255, 166, 163, 194);

        ObjectAnimator animateSqOne_1 = ObjectAnimator.ofArgb(sqOne, "backgroundColor", colorSqOne, colorSqTwo);
        ObjectAnimator animateSqTwo_1 = ObjectAnimator.ofArgb(sqTwo, "backgroundColor", colorSqTwo, colorSqThree);
        ObjectAnimator animateSqThree_1 = ObjectAnimator.ofArgb(sqThree, "backgroundColor", colorSqThree, colorSqFour);
        ObjectAnimator animateSqFour_1 = ObjectAnimator.ofArgb(sqFour, "backgroundColor", colorSqFour, colorSqFive);
        ObjectAnimator animateSqFive_1 = ObjectAnimator.ofArgb(sqFive, "backgroundColor", colorSqFive, colorSqOne);

        AnimatorSet animatorSetColorRunOne = new AnimatorSet();
        animatorSetColorRunOne.play(animateSqOne_1);
        animatorSetColorRunOne.play(animateSqTwo_1);
        animatorSetColorRunOne.play(animateSqThree_1);
        animatorSetColorRunOne.play(animateSqFour_1);
        animatorSetColorRunOne.play(animateSqFive_1);
        animatorSetColorRunOne.start();


        ObjectAnimator animateSqOne_2 = ObjectAnimator.ofArgb(sqOne, "backgroundColor", colorSqTwo, colorSqThree);
        ObjectAnimator animateSqTwo_2 = ObjectAnimator.ofArgb(sqTwo, "backgroundColor", colorSqThree, colorSqFour);
        ObjectAnimator animateSqThree_2 = ObjectAnimator.ofArgb(sqThree, "backgroundColor", colorSqFour, colorSqFive);
        ObjectAnimator animateSqFour_2 = ObjectAnimator.ofArgb(sqFour, "backgroundColor", colorSqFive, colorSqOne);
        ObjectAnimator animateSqFive_2 = ObjectAnimator.ofArgb(sqFive, "backgroundColor", colorSqOne, colorSqTwo);

        AnimatorSet animatorSetColorRunTwo = new AnimatorSet();
        animatorSetColorRunTwo.play(animateSqOne_2);
        animatorSetColorRunTwo.play(animateSqTwo_2);
        animatorSetColorRunTwo.play(animateSqThree_2);
        animatorSetColorRunTwo.play(animateSqFour_2);
        animatorSetColorRunTwo.play(animateSqFive_2);
        animatorSetColorRunTwo.start();


        ObjectAnimator animateSqOne_3 = ObjectAnimator.ofArgb(sqOne, "backgroundColor", colorSqThree, colorSqFour);
        ObjectAnimator animateSqTwo_3 = ObjectAnimator.ofArgb(sqTwo, "backgroundColor", colorSqFour, colorSqFive);
        ObjectAnimator animateSqThree_3 = ObjectAnimator.ofArgb(sqThree, "backgroundColor", colorSqFive, colorSqOne);
        ObjectAnimator animateSqFour_3 = ObjectAnimator.ofArgb(sqFour, "backgroundColor", colorSqOne, colorSqTwo);
        ObjectAnimator animateSqFive_3 = ObjectAnimator.ofArgb(sqFive, "backgroundColor", colorSqTwo, colorSqThree);

        AnimatorSet animatorSetColorRunThree = new AnimatorSet();
        animatorSetColorRunThree.play(animateSqOne_3);
        animatorSetColorRunThree.play(animateSqTwo_3);
        animatorSetColorRunThree.play(animateSqThree_3);
        animatorSetColorRunThree.play(animateSqFour_3);
        animatorSetColorRunThree.play(animateSqFive_3);
        animatorSetColorRunThree.start();


        ObjectAnimator animateSqOne_4 = ObjectAnimator.ofArgb(sqOne, "backgroundColor", colorSqFour, colorSqFive);
        ObjectAnimator animateSqTwo_4 = ObjectAnimator.ofArgb(sqTwo, "backgroundColor", colorSqFive, colorSqOne);
        ObjectAnimator animateSqThree_4 = ObjectAnimator.ofArgb(sqThree, "backgroundColor", colorSqOne, colorSqTwo);
        ObjectAnimator animateSqFour_4 = ObjectAnimator.ofArgb(sqFour, "backgroundColor", colorSqTwo, colorSqThree);
        ObjectAnimator animateSqFive_4 = ObjectAnimator.ofArgb(sqFive, "backgroundColor", colorSqThree, colorSqFour);

        AnimatorSet animatorSetColorRunFour = new AnimatorSet();
        animatorSetColorRunFour.play(animateSqOne_4);
        animatorSetColorRunFour.play(animateSqTwo_4);
        animatorSetColorRunFour.play(animateSqThree_4);
        animatorSetColorRunFour.play(animateSqFour_4);
        animatorSetColorRunFour.play(animateSqFive_4);
        animatorSetColorRunFour.start();

        ObjectAnimator animateSqOne_5 = ObjectAnimator.ofArgb(sqOne, "backgroundColor", colorSqFive, colorSqOne);
        ObjectAnimator animateSqTwo_5 = ObjectAnimator.ofArgb(sqTwo, "backgroundColor", colorSqOne, colorSqTwo);
        ObjectAnimator animateSqThree_5 = ObjectAnimator.ofArgb(sqThree, "backgroundColor", colorSqTwo, colorSqThree);
        ObjectAnimator animateSqFour_5 = ObjectAnimator.ofArgb(sqFour, "backgroundColor", colorSqThree, colorSqFour);
        ObjectAnimator animateSqFive_5 = ObjectAnimator.ofArgb(sqFive, "backgroundColor", colorSqFour, colorSqFive);

        AnimatorSet animatorSetColorRunFive = new AnimatorSet();
        animatorSetColorRunFive.play(animateSqOne_5);
        animatorSetColorRunFive.play(animateSqTwo_5);
        animatorSetColorRunFive.play(animateSqThree_5);
        animatorSetColorRunFive.play(animateSqFour_5);
        animatorSetColorRunFive.play(animateSqFive_5);
        animatorSetColorRunFive.start();

        animatorSetColorRunOne.setDuration(150);
        animatorSetColorRunTwo.setDuration(150);
        animatorSetColorRunThree.setDuration(150);
        animatorSetColorRunFour.setDuration(150);
        animatorSetColorRunFive.setDuration(150);


        AnimatorSet mainAnimatorSet = new AnimatorSet();
        mainAnimatorSet.playSequentially(animatorSetColorRunOne, animatorSetColorRunTwo, animatorSetColorRunThree,
                animatorSetColorRunFour, animatorSetColorRunFive);
        mainAnimatorSet.start();
    }


}



