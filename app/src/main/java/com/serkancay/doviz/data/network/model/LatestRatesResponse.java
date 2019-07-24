package com.serkancay.doviz.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class LatestRatesResponse {

    @SerializedName("rates")
    private Rates mRates;

    @SerializedName("base")
    private String mBase;

    @SerializedName("date")
    private String mDate;

    public Rates getRates() {
        return mRates;
    }

    public String getBase() {
        return mBase;
    }

    public String getDate() {
        return mDate;
    }

    public static class Rates {

        @SerializedName("TRY")
        private float mTry;

        public float getTry() {
            return mTry;
        }
    }

}
