package com.serkancay.doviz.ui.rates;

import com.serkancay.doviz.data.network.ApiHelper;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import com.serkancay.doviz.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesInteractor extends BaseInteractor {

    public RatesInteractor(final ApiHelper apiHelper) {
        super(apiHelper);
    }

    public Observable<LatestRatesResponse> getLatestRatesApiCall(String base) {
        return getApiHelper().getLatestRatesApiCall(base);
    }
}
