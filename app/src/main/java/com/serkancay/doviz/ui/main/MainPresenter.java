package com.serkancay.doviz.ui.main;

import com.serkancay.doviz.ui.base.BaseFragment;
import com.serkancay.doviz.ui.base.FragmentNavigation;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class MainPresenter implements FragmentNavigation.Presenter {

    private MainView mView;

    public MainPresenter(MainView view) {
        mView = view;
    }

    public void onDestroy() {
        mView = null;
    }

    @Override
    public void addFragment(final BaseFragment fragment, final boolean addToBackStack) {
        mView.setFragment(fragment, addToBackStack);
    }

    @Override
    public void setTitle(final String title) {
        mView.changeTitle(title);
    }

    @Override
    public void setDisplayHomeAsUpEnabled(final boolean isEnabled) {
        mView.setDisplayHomeAsUpEnabled(isEnabled);
    }

}
