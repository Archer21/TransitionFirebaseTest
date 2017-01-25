package com.archer.transitionfirebasetest.ui.activity;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.util.Helpers;
import com.archer.transitionfirebasetest.ui.fragment.HomeFragment;
import com.archer.transitionfirebasetest.ui.fragment.ProfileFragment;
import com.archer.transitionfirebasetest.ui.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class ContainerActivity extends BaseActivity {

    @BindView(R.id.bottombar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_search:
                        SearchFragment searchFragment = new SearchFragment();
                        Helpers.replaceFragment(getSupportFragmentManager(), R.id.main_container, searchFragment);
                        break;

                    case R.id.tab_home:
                        HomeFragment homeFragment= new HomeFragment();
                        Helpers.replaceFragment(getSupportFragmentManager(), R.id.main_container, homeFragment);
                        break;

                    case R.id.tab_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        Helpers.replaceFragment(getSupportFragmentManager(), R.id.main_container, profileFragment);
                        break;
                }
            }
        });

        if (savedInstanceState != null) {
            HomeFragment homeFragment = new HomeFragment();
            Helpers.replaceFragment(getSupportFragmentManager(), R.id.main_container, homeFragment);
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_container;
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




























