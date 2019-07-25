package com.serkancay.doviz.ui.rates.history;

import android.view.MenuItem;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.db.AppDatabase;
import com.serkancay.doviz.data.network.AppApiHelper;
import com.serkancay.doviz.data.network.model.Rate;
import com.serkancay.doviz.ui.base.BaseFragment;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class HistoryFragment extends BaseFragment implements HistoryView {

    @BindView(R.id.rvHistories)
    RecyclerView rvHistories;

    private HistoryPresenter mPresenter;

    private HistoryListAdapter mHistoryListAdapter;

    private HashMap<String, Rate> mRates;

    private String mBase;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    public void onCreated() {
        setHasOptionsMenu(true);
        mRates = new HashMap<>();
        mHistoryListAdapter = new HistoryListAdapter(context, mRates);
        rvHistories.setAdapter(mHistoryListAdapter);
        mPresenter = new HistoryPresenter(this, new HistoryInteractor(AppApiHelper.getApiHelper(),
                AppDatabase.getDatabase(context)));
    }

    @Override
    public void onResumed() {
        getNavigationPresenter().setTitle(mBase + "/TRY");
        getNavigationPresenter().setDisplayHomeAsUpEnabled(true);
        mPresenter.onResume(mBase);
    }

    @Override
    public void onDestroyed() {
        mPresenter.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.history, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miShare: {
                mPresenter.sendCurrencyToAllUsers(mBase);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBase(String base) {
        mBase = base;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateRates(HashMap<String, Rate> rates) {
        mRates.clear();
        mRates.putAll(rates);
        mHistoryListAdapter.notifyDataSetChanged();
    }
}
