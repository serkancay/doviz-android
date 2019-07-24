package com.serkancay.doviz.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by S.Serkan Cay on 15.05.2019
 */

public class BaseActivity extends AppCompatActivity {

    public Context context;

    public BaseActivity activity;

    public int getLayoutId() {
        return -1;
    }

    public void bindEvents() {
    }

    public void onCreated() {
    }

    public void onResumed() {
    }

    public void onPaused() {
    }

    public void onDestroyed() {
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;

        if (getLayoutId() != -1) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }

        onCreated();
        bindEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyed();
    }

    public void replaceFragment(View view, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(view.getId(), fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public void removeFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
