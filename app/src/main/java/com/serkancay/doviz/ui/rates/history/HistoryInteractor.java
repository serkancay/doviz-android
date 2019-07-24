package com.serkancay.doviz.ui.rates.history;

import com.serkancay.doviz.data.network.ApiHelper;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class HistoryInteractor extends BaseInteractor {


    public HistoryInteractor(final ApiHelper apiHelper) {
        super(apiHelper);
    }

    public Observable<HistoryRatesResponse> getHistoryRatesApiCall(String startDate, String endDate, String symbol,
            String base) {
        return getApiHelper().getHistoryRatesApiCall(startDate, endDate, symbol, base);
    }
}
