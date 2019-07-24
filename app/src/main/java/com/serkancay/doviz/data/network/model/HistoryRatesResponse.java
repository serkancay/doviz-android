package com.serkancay.doviz.data.network.model;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.List;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class HistoryRatesResponse {

    @SerializedName("rates")
    private HashMap<String, Rate> mRates;

    @SerializedName("base")
    private String mBase;

    public HashMap<String, Rate> getRates() {
        return mRates;
    }

    public String getBase() {
        return mBase;
    }
}
