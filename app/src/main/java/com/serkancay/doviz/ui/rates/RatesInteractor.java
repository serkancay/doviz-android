package com.serkancay.doviz.ui.rates;

import com.serkancay.doviz.data.db.AppDatabase;
import com.serkancay.doviz.data.db.entity.Rate;
import com.serkancay.doviz.data.network.ApiHelper;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import com.serkancay.doviz.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesInteractor extends BaseInteractor {

    public RatesInteractor(final ApiHelper apiHelper, final AppDatabase appDatabase) {
        super(apiHelper, appDatabase);
    }

    public Observable<LatestRatesResponse> getLatestRatesApiCall(String base) {
        return getApiHelper().getLatestRatesApiCall(base);
    }

    public void storeRate(Rate rate) {
        getAppDatabase().rateModel().insertRate(rate);
    }
}
