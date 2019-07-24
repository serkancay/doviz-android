package com.serkancay.doviz.ui.rates;

import android.provider.Telephony.Mms.Rate;
import com.serkancay.doviz.data.network.model.LatestRatesResponse.Rates;
import java.util.HashMap;
import java.util.List;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public interface RatesView {

    void showProgress();

    void hideProgress();

    void updateRate(String base, String date, Rates rates);

    void navigateToDetailScreen();

}
