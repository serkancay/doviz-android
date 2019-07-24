package com.serkancay.doviz.ui.main;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.ui.base.BaseActivity;
import com.serkancay.doviz.ui.base.BaseFragment;
import com.serkancay.doviz.ui.rates.RatesFragment;

public class MainActivity extends BaseActivity implements MainView {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @BindView(R.id.flContent)
    FrameLayout flContent;

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private RatesFragment frRates;

    private MainPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreated() {
        frRates = new RatesFragment();
        mPresenter = new MainPresenter(this);
        mPresenter.addFragment(frRates, false);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setFragment(final BaseFragment fragment, boolean addToBackStack) {
        fragment.attachPresenter(mPresenter);
        replaceFragment(flContent, fragment, addToBackStack);
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
