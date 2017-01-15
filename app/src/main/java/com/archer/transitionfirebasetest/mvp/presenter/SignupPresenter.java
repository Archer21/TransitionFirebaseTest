package com.archer.transitionfirebasetest.mvp.presenter;

import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.io.callback.SignupServerCallback;
import com.archer.transitionfirebasetest.mvp.interactor.SignupInteractor;
import com.archer.transitionfirebasetest.mvp.viewmodel.SignupViewModel;

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
     * Lifecycle methods
     */
    @Override
    public void onStart() {

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

    }

    @Override
    public void onFailedSignup() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onServerError() {

    }
}



































