package com.serkancay.doviz.ui.base;

import com.serkancay.doviz.data.db.AppDatabase;
import com.serkancay.doviz.data.network.ApiHelper;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public class BaseInteractor {

    private final ApiHelper mApiHelper;

    private AppDatabase mAppDatabase;

    public BaseInteractor(ApiHelper apiHelper, AppDatabase appDatabase) {
        mApiHelper = apiHelper;
        mAppDatabase = appDatabase;
    }

    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

}
