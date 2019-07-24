package com.serkancay.doviz.ui.base;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public interface FragmentNavigation {

    interface View {

        void attachPresenter(Presenter presenter);

    }

    interface Presenter {

        void addFragment(BaseFragment fragment, boolean addToBackStack);

        void setTitle(String title);

        void setDisplayHomeAsUpEnabled(boolean isEnabled);
    }
}
