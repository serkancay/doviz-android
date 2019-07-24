package com.serkancay.doviz;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
import com.serkancay.doviz.data.network.TLSSocketFactory;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import okhttp3.OkHttpClient;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        try {
            AndroidNetworking.initialize(getApplicationContext(),
                    new OkHttpClient().newBuilder().sslSocketFactory(new TLSSocketFactory()).build());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
