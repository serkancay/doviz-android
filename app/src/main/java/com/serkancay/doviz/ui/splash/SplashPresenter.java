package com.serkancay.doviz.ui.splash;

import android.os.Handler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class SplashPresenter {

    private static final int SPLASH_DELAY_TIME = 2000;

    private SplashView mView;

    public SplashPresenter(SplashView view) {
        mView = view;
    }

    public void onResume() {
        finishWithDelay();
    }

    public void onDestroy() {
        mView = null;
    }

    public void finishWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.navigateToHome();
            }
        }, SPLASH_DELAY_TIME);
    }


}
