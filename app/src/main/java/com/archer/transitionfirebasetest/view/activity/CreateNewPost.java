package com.archer.transitionfirebasetest.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;

public class CreateNewPost extends BaseActivity {



    @Override
    public int getLayout() {
        return R.layout.activity_create_new_post;
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
