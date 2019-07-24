package com.serkancay.doviz.ui.rates;

import android.provider.Telephony.Mms.Rate;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.network.AppApiHelper;
import com.serkancay.doviz.data.network.model.LatestRatesResponse.Rates;
import com.serkancay.doviz.ui.base.BaseFragment;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesFragment extends BaseFragment implements RatesView {

    @BindView(R.id.rvRates)
    RecyclerView rvRates;

    private RatesPresenter mPresenter;

    private HashMap<String, Rates> mRatesHashMap;

    private RateListAdapter mRateListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rates;
    }

    @Override
    public void onCreated() {
        mRatesHashMap = new HashMap<>();
        mRateListAdapter = new RateListAdapter(context, mRatesHashMap);
        rvRates.setAdapter(mRateListAdapter);
        mPresenter = new RatesPresenter(this, new RatesInteractor(AppApiHelper.getApiHelper()));
    }

    @Override
    public void onResumed() {
        mPresenter.onResume();
    }

    @Override
    public void onDestroyed() {
        mPresenter.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateRate(final String base, final String date, final Rates rates) {
        mRatesHashMap.put(base, rates);
        mRateListAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToDetailScreen() {

    }
}
