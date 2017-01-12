package com.archer.transitionfirebasetest.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by alanaliaga on 31/12/16.
 */

public abstract class BaseFragment extends Fragment{
    protected Context CONTEXT;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CONTEXT = context;
    }

    /**
     * Override fragment fragment lifecycle methods
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindViews();
    }


    /**
     * Private methods that all fragments have implemented
     */

    private void bindViews (View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
    }

    private void unbindViews() {
        unbinder.unbind();
    }




    /**
     * Abstract methods that all fragments must implement
     */

    public abstract int getFragmentLayout ();
    public abstract BasePresenter getPresenter ();
}

































