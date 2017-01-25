package com.archer.transitionfirebasetest.ui.activity;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.util.Helpers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    /**
     * Bind views with Butterkniffe
     */
    @BindView(R.id.login_input_layout_email)
    TextInputLayout loginInputLayoutEmail;
    @BindView(R.id.login_input_email)
    TextInputEditText loginInputEmail;

    @BindView(R.id.login_input_layout_password)
    TextInputLayout loginInputLayoutPassword;
    @BindView(R.id.login_input_password)
    TextInputEditText loginInputPassword;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    private boolean checkEmail (String email) {
        if (email.isEmpty() || !Helpers.isEmailValid(email)) {
            loginInputLayoutEmail.setErrorEnabled(true);
            loginInputLayoutEmail.setError(getResources().getString(R.string.err_msg_email));
            loginInputEmail.setError(getResources().getString(R.string.err_msg_required));
            Helpers.requestFocus(LoginActivity.this, loginInputEmail);
            return false;
        }

        loginInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword (String password) {
        if (password.isEmpty() || !Helpers.isPasswordValid(password)) {
            loginInputLayoutPassword.setErrorEnabled(true);
            loginInputLayoutPassword.setError(getResources().getString(R.string.err_msg_password));
            loginInputPassword.setError(getResources().getString(R.string.err_msg_required));
            Helpers.requestFocus(LoginActivity.this, loginInputPassword);
            return false;
        }

        loginInputLayoutPassword.setErrorEnabled(false);
        return true;
    }


    public void goToSignupScreen (View view) {
        Helpers.navigate(LoginActivity.this, SignupActivity.class);
        finish();
    }

    public void submitLoginForm (View view) {
        String email = loginInputEmail.getText().toString().trim();
        String password = loginInputPassword.getText().toString().trim();

        if (!checkEmail(email)) return;
        if (!checkPassword(password)) return;

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    FirebaseUser user = task.getResult().getUser();
                    String username = user.getDisplayName();
                    SharedPreferences.Editor sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE).edit();
                    sharedPreferences.putString("username", username);
                    sharedPreferences.apply();

                    if (!task.isSuccessful()) {

                        progressBar.setVisibility(View.GONE);
                        Snackbar.make(coordinatorLayout, "Some error ocurred, try again", Snackbar.LENGTH_LONG).show();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Helpers.navigate(LoginActivity.this, MainActivity.class);
                    }
                }
            });

    }



    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}







































