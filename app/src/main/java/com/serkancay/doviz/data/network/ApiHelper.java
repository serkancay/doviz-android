package com.serkancay.doviz.data.network;


import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import io.reactivex.Observable;

/**
 * Created by S.Serkan Cay on 16.05.2019
 */

public interface ApiHelper {

    Observable<LatestRatesResponse> getLatestRatesApiCall(String base);

}
