package com.serkancay.doviz.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import butterknife.BindView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.serkancay.doviz.R;
import com.serkancay.doviz.helper.LocaleHelper;
import com.serkancay.doviz.ui.base.BaseActivity;
import com.serkancay.doviz.ui.base.BaseFragment;
import com.serkancay.doviz.ui.rates.RatesFragment;
import com.serkancay.doviz.ui.settings.SettingsFragment;

public class MainActivity extends BaseActivity implements MainView {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @BindView(R.id.flContent)
    FrameLayout flContent;

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private RatesFragment frRates;

    private SettingsFragment frSettings;

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
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
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
                    clearBackStack();
                    if (frRates == null) {
                        frRates = new RatesFragment();
                    }
                    mPresenter.addFragment(frRates, false);
                    return true;
                }
                case R.id.miSettings: {
                    clearBackStack();
                    frSettings = new SettingsFragment();
                    mPresenter.addFragment(frSettings, false);
                    return true;
                }
            }
            return false;
        }
    };
}
