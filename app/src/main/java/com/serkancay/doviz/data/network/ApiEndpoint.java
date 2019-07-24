package com.serkancay.doviz.data.network;


import com.serkancay.doviz.BuildConfig;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public final class ApiEndpoint {

    public static final String ENDPOINT_LATEST_RATES = BuildConfig.BASE_URL + "/latest";

    public static final String ENDPOINT_HISTORY_RATES = BuildConfig.BASE_URL + "/history";

    private ApiEndpoint() {
    }

}
