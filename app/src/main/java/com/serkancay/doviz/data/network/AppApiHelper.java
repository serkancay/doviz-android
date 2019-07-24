package com.serkancay.doviz.data.network;


import android.util.Log;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.serkancay.doviz.BuildConfig;
import com.serkancay.doviz.Constants;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import io.reactivex.Observable;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    public Observable<JSONObject> sendCurrencyInfoToAllUsers(final String title, final String message) {
        JSONObject notification = new JSONObject();
        JSONObject notificationBody = new JSONObject();
        try {
            notificationBody.put("title", title);
            notificationBody.put("message", message);

            notification.put("to", "/topics/" + Constants.FCM_TOPIC_ALL_USERS);
            notification.put("data", notificationBody);
        } catch (JSONException e) {

        }
        return Rx2AndroidNetworking.post(ApiEndpoint.ENDPOINT_FCM)
                .addHeaders("Authorization", "key=" + BuildConfig.FCM_SERVER_KEY)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(notification)
                .build().getJSONObjectObservable();
    }

    private AppApiHelper() {
    }

}
