package com.serkancay.doviz.ui.rates.history;

import android.annotation.SuppressLint;
import android.util.Log;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import com.serkancay.doviz.util.CurrencyFormatter;
import com.serkancay.doviz.util.DateUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import org.json.JSONObject;

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

    @SuppressLint("CheckResult")
    public void sendCurrencyToAllUsers(String base) {
        mInteractor.getLatestRatesApiCall(base).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatestRatesResponse>() {
                    @Override
                    public void accept(final LatestRatesResponse latestRatesResponse) throws Exception {
                        if (latestRatesResponse != null && latestRatesResponse.getRate() != null) {
                            Log.d("RATE", latestRatesResponse.getRate().getTry() + "");
                            sendNotificationMessage(base, CurrencyFormatter
                                    .format(CurrencyFormatter.TRY, latestRatesResponse.getRate().getTry()));
                        }
                        if (mView != null) {
                            mView.hideProgress();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        Log.d("ERROR", throwable.getMessage() + "");

                        if (mView != null) {
                            mView.hideProgress();
                        }
                    }
                });
    }

    private void sendNotificationMessage(String title, String message) {
        mInteractor.sendCurrencyInfoToAllUsers(title, message).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JSONObject>() {
                    @Override
                    public void accept(final JSONObject jsonObject) throws Exception {
                        Log.d("SUCCESS", jsonObject.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        Log.d("FAIL", throwable.getMessage());
                    }
                });
    }

}
