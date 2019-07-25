package com.serkancay.doviz.ui.rates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.serkancay.doviz.data.db.entity.Rate;
import com.serkancay.doviz.data.network.model.LatestRatesResponse;
import com.serkancay.doviz.util.keystore.CryptorUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesPresenter {

    private RatesView mView;

    private RatesInteractor mInteractor;

    private Context mContext;

    public RatesPresenter(Context context, RatesView view, RatesInteractor interactor) {
        mView = view;
        mInteractor = interactor;
        mContext = context;
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
                            String base = latestRatesResponse.getBase();
                            String value = latestRatesResponse.getRate().getTry() + "";
                            String date = latestRatesResponse.getDate();
                            String encryptedBase = CryptorUtil.encrypt(mContext, base);
                            String encryptedValue = CryptorUtil.encrypt(mContext, value);
                            String encryptedDate = CryptorUtil.encrypt(mContext, date);
                            Rate rate = new Rate(encryptedBase,
                                    encryptedValue, encryptedDate);
                            mInteractor.storeRate(rate);
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
                            String base = latestRatesResponse.getBase();
                            String value = latestRatesResponse.getRate().getTry() + "";
                            String date = latestRatesResponse.getDate();
                            String encryptedBase = CryptorUtil.encrypt(mContext, base);
                            String encryptedValue = CryptorUtil.encrypt(mContext, value);
                            String encryptedDate = CryptorUtil.encrypt(mContext, date);
                            Rate rate = new Rate(encryptedBase,
                                    encryptedValue, encryptedDate);
                            mInteractor.storeRate(rate);
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
                            String base = latestRatesResponse.getBase();
                            String value = latestRatesResponse.getRate().getTry() + "";
                            String date = latestRatesResponse.getDate();
                            String encryptedBase = CryptorUtil.encrypt(mContext, base);
                            String encryptedValue = CryptorUtil.encrypt(mContext, value);
                            String encryptedDate = CryptorUtil.encrypt(mContext, date);
                            Rate rate = new Rate(encryptedBase,
                                    encryptedValue, encryptedDate);
                            mInteractor.storeRate(rate);
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
    }

    public void onDestroy() {
        mView = null;
    }

    public void navigate(String base) {
        mView.navigateToHistoryScreen(base);
    }

}
