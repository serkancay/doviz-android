package com.serkancay.doviz.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class LatestRatesResponse {

    @SerializedName("rates")
    private Rate mRate;

    @SerializedName("base")
    private String mBase;

    @SerializedName("date")
    private String mDate;

    public Rate getRate() {
        return mRate;
    }

    public String getBase() {
        return mBase;
    }

    public String getDate() {
        return mDate;
    }

}
