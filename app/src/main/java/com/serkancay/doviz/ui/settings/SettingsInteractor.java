package com.serkancay.doviz.ui.settings;

import com.serkancay.doviz.data.db.AppDatabase;
import com.serkancay.doviz.data.db.entity.Rate;
import com.serkancay.doviz.data.network.ApiHelper;
import com.serkancay.doviz.ui.base.BaseInteractor;
import java.util.List;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public class SettingsInteractor extends BaseInteractor {

    public SettingsInteractor(final ApiHelper apiHelper,
            final AppDatabase appDatabase) {
        super(apiHelper, appDatabase);
    }

    public List<Rate> getRates() {
        return getAppDatabase().rateModel().getAllRates();
    }
}
