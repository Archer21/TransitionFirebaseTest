package com.archer.transitionfirebasetest.mvp.interactor;

import com.archer.transitionfirebasetest.io.callback.SignupServerCallback;

/**
 * Created by archer on 14-01-17.
 */

public class SignupInteractor {
    private SignupServerCallback presenter;

    public SignupInteractor(SignupServerCallback presenter) {
        this.presenter = presenter;
    }

    public void submitSignupForm () {

    }
}
