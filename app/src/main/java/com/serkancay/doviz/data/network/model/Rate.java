package com.serkancay.doviz.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class Rate {

    @SerializedName("TRY")
    private float mTry;

    public float getTry() {
        return mTry;
    }

}
