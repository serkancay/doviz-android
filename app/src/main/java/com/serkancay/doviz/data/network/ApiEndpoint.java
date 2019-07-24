package com.serkancay.doviz.data.network;

import com.serkancay.rahatlaticisesler.BuildConfig;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public final class ApiEndpoint {

    public static final String ENDPOINT_FAVORITE_LIST = BuildConfig.BASE_URL + "/favorite_list.html";

    public static final String ENDPOINT_CATEGORY_LIST = BuildConfig.BASE_URL + "/categories";

    private ApiEndpoint() {
    }

}
