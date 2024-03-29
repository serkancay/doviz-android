package com.serkancay.doviz.data.network;


import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import io.reactivex.Observable;
import org.json.JSONObject;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public interface ApiHelper {

    Observable<LatestRatesResponse> getLatestRatesApiCall(String base);

    Observable<HistoryRatesResponse> getHistoryRatesApiCall(String startDate, String endDate, String symbol, String base);

    Observable<JSONObject> sendCurrencyInfoToAllUsers(String title, String message);

}
