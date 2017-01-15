package com.archer.transitionfirebasetest.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.mvp.viewmodel.SignupViewModel;
import com.archer.transitionfirebasetest.util.Helpers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class SignupActivity extends BaseActivity implements SignupViewModel {

    /**
     * Bind views with Butterkniffe
     */
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
    private FirebaseAuth auth;


    /**
     * Activity Lifecycle methods
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    /**
     * Activity methods
     */

    private boolean checkEmail (String email) {
        if (email.isEmpty() || !Helpers.isEmailValid(email)) {
            signupInputLayoutEmail.setErrorEnabled(true);
            signupInputLayoutEmail.setError(getResources().getString(R.string.err_msg_email));
            signupInputEmail.setError(getResources().getString(R.string.err_msg_required));
            Helpers.requestFocus(SignupActivity.this, signupInputEmail);
            return false;
        }

        signupInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword (String password) {
        if (password.isEmpty() || !Helpers.isPasswordValid(password)) {
            signupInputLayoutPassword.setErrorEnabled(true);
            signupInputLayoutPassword.setError(getResources().getString(R.string.err_msg_password));
            signupInputPassword.setError(getResources().getString(R.string.err_msg_required));
            Helpers.requestFocus(SignupActivity.this, signupInputPassword);
            return false;
        }

        signupInputLayoutPassword.setErrorEnabled(false);
        return true;
    }


    /**
     * onClick methods from the view
     */
    public void goToLoginScreen (View view) {
        Helpers.navigate(SignupActivity.this, LoginActivity.class);
    }

    public void submitForm (View view) {
        String email = signupInputEmail.getText().toString().trim();
        String password = signupInputPassword.getText().toString().trim();

        if (!checkEmail(email)) {
            return;
        }
        if (!checkPassword(password)) {
            return;
        }

        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutPassword.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Snackbar.make(coordinatorLayout, getResources().getString(R.string.email_password_unsuccessful_signup_message) + " " + task.getException(), Snackbar.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Helpers.navigate(SignupActivity.this, LoginActivity.class);
                        finish();
                    }
                }
            });
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
        return null;
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

    }

    @Override
    public void showPasswordError() {
        signupInputLayoutPassword.setErrorEnabled(true);
        signupInputLayoutPassword.setError(getResources().getString(R.string.err_msg_password));
        signupInputPassword.setError(getResources().getString(R.string.err_msg_required));
    }
}











































































