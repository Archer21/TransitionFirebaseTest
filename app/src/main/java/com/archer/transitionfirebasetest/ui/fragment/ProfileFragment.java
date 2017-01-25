package com.archer.transitionfirebasetest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseFragment;
import com.archer.transitionfirebasetest.common.BasePresenter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}































