package com.serkancay.doviz.ui.splash;

import com.serkancay.doviz.ui.main.MainActivity;
import com.serkancay.doviz.ui.base.BaseActivity;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class SplashActivity extends BaseActivity implements SplashView {

    private SplashPresenter mPresenter;

    @Override
    public void onCreated() {
        mPresenter = new SplashPresenter(this);
    }

    @Override
    public void onResumed() {
        mPresenter.onResume();
    }

    @Override
    public void onDestroyed() {
        mPresenter.onDestroy();
    }

    @Override
    public void navigateToHome() {
        MainActivity.start(context);
        finish();
    }

    @Override
    public void showError() {

    }
}
