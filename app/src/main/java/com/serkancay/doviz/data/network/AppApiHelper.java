package com.serkancay.doviz.data.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import io.reactivex.Observable;

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

    @Override
    public Observable<LatestRatesResponse> getLatestRatesApiCall(final String base) {
        return Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_LATEST_RATES)
                .addQueryParameter("base", base)
                .build()
                .getObjectObservable(LatestRatesResponse.class);
    }

    @Override
    public Observable<HistoryRatesResponse> getHistoryRatesApiCall(final String startDate, final String endDate,
            final String symbol,
            final String base) {
        return Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_HISTORY_RATES)
                .addQueryParameter("start_at", startDate)
                .addQueryParameter("end_at", endDate)
                .addQueryParameter("symbols", symbol)
                .addQueryParameter("base", base)
                .build()
                .getObjectObservable(HistoryRatesResponse.class);
    }

    private AppApiHelper() {
    }

}
