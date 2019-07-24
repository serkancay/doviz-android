package com.serkancay.doviz.ui.rates.history;

import com.serkancay.doviz.data.network.model.Rate;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public interface HistoryView {

    void showProgress();

    void hideProgress();

    void updateRates(HashMap<String, Rate> rates);

}
