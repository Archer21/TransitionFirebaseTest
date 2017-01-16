package com.archer.transitionfirebasetest.mvp.presenter;

import android.util.Log;

import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.io.callback.SignupServerCallback;
import com.archer.transitionfirebasetest.mvp.interactor.SignupInteractor;
import com.archer.transitionfirebasetest.mvp.viewmodel.SignupViewModel;
import com.archer.transitionfirebasetest.util.Helpers;

import java.lang.ref.WeakReference;

/**
 * Created by archer on 14-01-17.
 */

public class SignupPresenter extends BasePresenter implements SignupServerCallback {
    private WeakReference<SignupViewModel> mView;
    private SignupInteractor mInteractor;

    public SignupPresenter(SignupViewModel view) {
        this.mView = new WeakReference<>(view);
        this.mInteractor = new SignupInteractor(this);
    }


    /**
     * Presenter methods
     */

    public void checkInformation (String email, String password) {
        if (!checkEmail(email)) {
            return;
        }
        if (!checkPassword(password)) {
            return;
        }

        mView.get().showProgressBar();
        mInteractor.createUserWithEmailAndPassword(email, password);
    }

    private boolean checkEmail (String email) {
        if (email.isEmpty() || !Helpers.isEmailValid(email)) {
            mView.get().showEmailError();
            mView.get().focusInputEmail();
            return false;
        }
        return true;
    }

    private boolean checkPassword (String password) {
        if (password.isEmpty() || !Helpers.isPasswordValid(password)) {
            mView.get().showPasswordError();
            mView.get().focusInputPassword();
            return false;
        }
        return true;
    }


    /**
     * Lifecycle methods
     */
    @Override
    public void onStart() {
        Log.e("SIGNUPPRESENTER", "Testing something");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {
        mView = null;
    }


    /**
     * ViewModel methods available for presenter
     */
    @Override
    public void onSuccessSignup() {
        mView.get().hideProgressBar();
        mView.get().goLoginScreen();
    }

    @Override
    public void onFailedSignup() {
        mView.get().showFirebaseSignupError();
        mView.get().hideProgressBar();
    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onServerError() {

    }
}



































