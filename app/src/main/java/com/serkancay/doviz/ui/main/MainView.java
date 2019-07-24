package com.serkancay.doviz.ui.main;

import com.serkancay.doviz.ui.base.BaseFragment;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public interface MainView {

    void setFragment(BaseFragment fragment, boolean addToBackStack);

    void changeTitle(String title);

    void setDisplayHomeAsUpEnabled(boolean isEnabled);

}
