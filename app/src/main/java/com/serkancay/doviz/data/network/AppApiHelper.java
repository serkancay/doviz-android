package com.serkancay.doviz.data.network;



/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public class AppApiHelper implements ApiHelper {

    private static AppApiHelper sInstance;

    public static AppApiHelper getApiHelper() {
        if (sInstance == null) {
            sInstance = new AppApiHelper();
        }
        return sInstance;
    }

    private AppApiHelper() {
    }

}
