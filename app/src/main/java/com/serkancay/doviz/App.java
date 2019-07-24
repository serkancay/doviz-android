package com.serkancay.doviz;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.preference.PreferenceManager;
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

    public void changeLang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = getApplicationContext().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            getApplicationContext().createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
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
