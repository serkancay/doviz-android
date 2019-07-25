package com.serkancay.doviz.ui.settings;

import com.serkancay.doviz.data.db.entity.Rate;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public interface SettingsView {

    void updateSpinner(LinkedHashMap<String, String> languages);

    void updateRateList(List<Rate> rates);
}
