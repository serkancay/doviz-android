package com.serkancay.doviz.ui.settings;

import android.content.Context;
import android.content.Intent;
import com.serkancay.doviz.R;
import java.util.LinkedHashMap;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public class SettingsPresenter {

    private SettingsView mView;

    private Context mContext;

    public SettingsPresenter(Context context, SettingsView view) {
        mView = view;
        mContext = context;
    }

    public void onResume() {
        if (mView != null) {
            mView.updateSpinner(generateLanguages());
        }
    }

    public void onDestroy() {
        mView = null;
    }

    public void restartApp() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    private LinkedHashMap<String, String> generateLanguages() {
        LinkedHashMap<String, String> languages = new LinkedHashMap<>();
        languages.put("none", mContext.getResources().getString(R.string.settings_fragment_spinner_select));
        languages.put("tr", mContext.getResources().getString(R.string.settings_fragment_spinner_turkish));
        languages.put("ar", mContext.getResources().getString(R.string.settings_fragment_spinner_arabic));
        return languages;
    }

}
