package com.archer.transitionfirebasetest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.mvp.presenter.SignupPresenter;
import com.archer.transitionfirebasetest.mvp.viewmodel.SignupViewModel;
import com.archer.transitionfirebasetest.util.Helpers;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class SignupActivity extends BaseActivity implements SignupViewModel {

    /**
     * Bind views with Butterkniffe
     */
    @BindView(R.id.signup_input_layout_username)
    TextInputLayout signupInputLayoutUsername;
    @BindView(R.id.signup_input_username)
    TextInputEditText signupInputUsername;

    @BindView(R.id.signup_input_layout_email)
    TextInputLayout signupInputLayoutEmail;
    @BindView(R.id.signup_input_email)
    TextInputEditText signupInputEmail;

    @BindView(R.id.signup_input_layout_password)
    TextInputLayout signupInputLayoutPassword;
    @BindView(R.id.signup_input_password)
    TextInputEditText signupInputPassword;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    /**
     * References
     */
    SignupPresenter presenter;
    FirebaseAuth auth;


    /**
     * Activity Lifecycle methods
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresenter(this);
        auth = FirebaseAuth.getInstance();
    }

    /**
     * Activity methods
     */




    /**
     * onClick methods from the view
     */
    public void goToLoginScreen (View view) {
        Helpers.navigate(SignupActivity.this, LoginActivity.class);
    }

    public void submitForm (View view) {
        String email = signupInputEmail.getText().toString().trim();
        String password = signupInputPassword.getText().toString().trim();
        String username = signupInputUsername.getText().toString();

        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutPassword.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);

//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (!task.isSuccessful()) {
//                        Snackbar.make(coordinatorLayout, getResources().getString(R.string.email_password_unsuccessful_signup_message) + " " + task.getException(), Snackbar.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                    } else {
//                        FirebaseUser user = task.getResult().getUser();
//                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                .setDisplayName(signupInputUsername.getText().toString())
//                                .build();
//
//                        user.updateProfile(profileUpdates);
//                        Helpers.navigate(SignupActivity.this, LoginActivity.class);
//                        finish();
//                    }
//                }
//            });
        presenter.checkInformation(username, email, password);
    }


    /**
     * Override methods from BaseActivity
     */
    @Override
    public int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }


    /**
     * Override methods from SignupViewModel
     */
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmailError() {
        signupInputLayoutEmail.setErrorEnabled(true);
        signupInputLayoutEmail.setError(getResources().getString(R.string.err_msg_email));
        signupInputEmail.setError(getResources().getString(R.string.err_msg_required));
    }

    @Override
    public void showPasswordError() {
        signupInputLayoutPassword.setErrorEnabled(true);
        signupInputLayoutPassword.setError(getResources().getString(R.string.err_msg_password));
        signupInputPassword.setError(getResources().getString(R.string.err_msg_required));
    }

    @Override
    public void focusInputEmail() {
        Helpers.requestFocus(SignupActivity.this, signupInputEmail);
    }

    @Override
    public void focusInputPassword() {
        Helpers.requestFocus(SignupActivity.this, signupInputPassword);
    }

    @Override
    public void showFirebaseSignupError () {
        Snackbar.make(coordinatorLayout, getResources().getString(R.string.email_password_unsuccessful_signup_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void goLoginScreen() {
        Helpers.navigate(SignupActivity.this, LoginActivity.class);
        finish();
    }
}











































































