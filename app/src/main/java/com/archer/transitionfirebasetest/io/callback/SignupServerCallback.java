package com.archer.transitionfirebasetest.io.callback;

/**
 * Created by archer on 14-01-17.
 */

public interface SignupServerCallback extends ServerCallback {
    void onSuccessSignup ();
    void onFailedSignup ();
}
