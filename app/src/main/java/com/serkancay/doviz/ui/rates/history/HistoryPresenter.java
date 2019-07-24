package com.serkancay.doviz.ui.rates.history;

import android.util.Log;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.util.DateUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class HistoryPresenter {

    private HistoryView mView;

    private HistoryInteractor mInteractor;

    public HistoryPresenter(HistoryView view, HistoryInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    public void onResume(String base) {
        Log.d("DATES", DateUtil.getNow() + "***" + DateUtil.getOneYearAgo());
        mInteractor.getHistoryRatesApiCall(DateUtil.getOneYearAgo(), DateUtil.getNow(), "TRY", base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HistoryRatesResponse>() {
                    @Override
                    public void accept(final HistoryRatesResponse historyRatesResponse) throws Exception {
                        Log.d("TRY", historyRatesResponse.getRates().get("2018-12-19").getTry() + "");
                        if (mView != null) {
                            mView.updateRates(historyRatesResponse.getRates());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        Log.d("ERROR", throwable.getMessage() + "");
                    }
                });
    }

    public void onDestroy() {
        mView = null;
    }

}
