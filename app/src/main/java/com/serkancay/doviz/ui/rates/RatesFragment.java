package com.serkancay.doviz.ui.rates;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.db.AppDatabase;
import com.serkancay.doviz.data.network.AppApiHelper;
import com.serkancay.doviz.data.network.model.Rate;
import com.serkancay.doviz.ui.base.BaseFragment;
import com.serkancay.doviz.ui.rates.RateListAdapter.Callback;
import com.serkancay.doviz.ui.rates.history.HistoryFragment;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RatesFragment extends BaseFragment implements RatesView {

    @BindView(R.id.rvRates)
    RecyclerView rvRates;

    private RatesPresenter mPresenter;

    private HashMap<String, Rate> mRatesHashMap;

    private RateListAdapter mRateListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rates;
    }

    @Override
    public void onCreated(Bundle bundle) {
        mRatesHashMap = new HashMap<>();
        mRateListAdapter = new RateListAdapter(context, mRatesHashMap);
        rvRates.setAdapter(mRateListAdapter);
        mPresenter = new RatesPresenter(context, this,
                new RatesInteractor(AppApiHelper.getApiHelper(), AppDatabase.getDatabase(context)));
    }

    @Override
    public void bindEvents() {
        mRateListAdapter.setCallback(mCallback);
    }

    @Override
    public void onResumed() {
        getNavigationPresenter().setTitle(getResources().getString(R.string.rates_fragment_title));
        getNavigationPresenter().setDisplayHomeAsUpEnabled(false);
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
    public void updateRate(final String base, final String date, final Rate rate) {
        mRatesHashMap.put(base, rate);
        mRateListAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToHistoryScreen(final String base) {
        HistoryFragment frHistory = new HistoryFragment();
        frHistory.setBase(base);
        getNavigationPresenter().addFragment(frHistory, true);
    }

    private Callback mCallback = new Callback() {
        @Override
        public void onItemClicked(final String base) {
            mPresenter.navigate(base);
        }
    };

}
