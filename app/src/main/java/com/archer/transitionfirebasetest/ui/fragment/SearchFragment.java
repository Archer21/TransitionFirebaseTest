package com.archer.transitionfirebasetest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseFragment;
import com.archer.transitionfirebasetest.common.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
