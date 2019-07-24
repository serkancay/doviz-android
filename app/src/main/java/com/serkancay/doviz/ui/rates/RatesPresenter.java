package com.serkancay.doviz.ui.rates;

import android.annotation.SuppressLint;
import android.util.Log;
import com.serkancay.doviz.data.network.model.HistoryRatesResponse;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesPresenter {

    private RatesView mView;

    private RatesInteractor mInteractor;

    public RatesPresenter(RatesView view, RatesInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @SuppressLint("CheckResult")
    public void onResume() {
        if (mView != null) {
            mView.showProgress();
        }
        mInteractor.getLatestRatesApiCall("USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatestRatesResponse>() {
                    @Override
                    public void accept(final LatestRatesResponse latestRatesResponse) throws Exception {
                        if (latestRatesResponse != null && latestRatesResponse.getRate() != null) {
                            Log.d("RATE", latestRatesResponse.getRate().getTry() + "");
                            if (mView != null) {
                                mView.updateRate(latestRatesResponse.getBase(), latestRatesResponse.getDate(),
                                        latestRatesResponse.getRate());
                            }
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
        mInteractor.getLatestRatesApiCall("EUR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatestRatesResponse>() {
                    @Override
                    public void accept(final LatestRatesResponse latestRatesResponse) throws Exception {
                        if (latestRatesResponse != null && latestRatesResponse.getRate() != null) {
                            Log.d("RATE", latestRatesResponse.getRate().getTry() + "");
                            if (mView != null) {
                                mView.updateRate(latestRatesResponse.getBase(), latestRatesResponse.getDate(),
                                        latestRatesResponse.getRate());
                            }
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
        mInteractor.getLatestRatesApiCall("JPY")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatestRatesResponse>() {
                    @Override
                    public void accept(final LatestRatesResponse latestRatesResponse) throws Exception {
                        if (latestRatesResponse != null && latestRatesResponse.getRate() != null) {
                            Log.d("RATE", latestRatesResponse.getRate().getTry() + "");
                            if (mView != null) {
                                mView.updateRate(latestRatesResponse.getBase(), latestRatesResponse.getDate(),
                                        latestRatesResponse.getRate());
                            }
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
        mInteractor.getHistoryRatesApiCall("2018-01-01", "2019-07-01", "TRY", "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HistoryRatesResponse>() {
                    @Override
                    public void accept(final HistoryRatesResponse historyRatesResponse) throws Exception {
                        Log.d("TRY", historyRatesResponse.getRates().get("2018-12-19").getTry() + "");
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

    public void navigate(String base) {
        mView.navigateToHistoryScreen(base);
    }

}
