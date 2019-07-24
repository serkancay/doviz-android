package com.serkancay.doviz.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.ui.base.BaseActivity;
import com.serkancay.doviz.ui.base.BaseFragment;

public class MainActivity extends BaseActivity implements MainView {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private MainPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreated() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    public void onDestroyed() {
        mPresenter.onDestroy();
    }

    @Override
    public void bindEvents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener);
    }

    @Override
    public void setFragment(final BaseFragment fragment, boolean addToBackStack) {
    }

    @Override
    public void changeTitle(final String title) {
        setTitle(title);
    }

    @Override
    public void setDisplayHomeAsUpEnabled(final boolean isEnabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isEnabled);
        }
    }

    private OnNavigationItemSelectedListener mNavigationItemSelectedListener
            = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.miExchangeRates: {
                    return true;
                }
                case R.id.miSettings: {
                    return true;
                }
            }
            return false;
        }
    };
}
