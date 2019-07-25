package com.serkancay.doviz;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.androidnetworking.AndroidNetworking;
import com.serkancay.doviz.data.network.TLSSocketFactory;
import com.serkancay.doviz.data.preferences.PreferencesHelper;
import com.serkancay.doviz.helper.LocaleHelper;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import okhttp3.OkHttpClient;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class App extends Application {

    private PreferencesHelper mPreferencesHelper;

    private Locale mLocale = null;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mLocale != null) {
            Locale.setDefault(mLocale);
            Configuration config = new Configuration(newConfig);
            config.locale = mLocale;
            getBaseContext().getResources()
                    .updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferencesHelper = new PreferencesHelper(this);
        initOkHttpClient();
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
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
