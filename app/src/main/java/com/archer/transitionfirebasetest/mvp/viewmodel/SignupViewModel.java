package com.archer.transitionfirebasetest.mvp.viewmodel;

/**
 * Created by archer on 14-01-17.
 */

public interface SignupViewModel {
    void showProgressBar ();
    void hideProgressBar ();
    void showEmailError ();
    void showPasswordError ();
}
