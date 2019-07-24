package com.serkancay.doviz.ui.rates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.network.model.LatestRatesResponse.Rates;
import com.serkancay.doviz.ui.rates.RateListAdapter.RateHolder;
import com.serkancay.doviz.util.CurrencyFormatter;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class RateListAdapter extends Adapter<RateHolder> {

    static class RateHolder extends ViewHolder {

        @BindView(R.id.tvValue)
        TextView tvValue;

        @BindView(R.id.tvUnit)
        TextView tvUnit;

        RateHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final int RESOURCE = R.layout.item_rate_list;

    private HashMap<String, Rates> mRatesHashMap;

    private String[] mKeys;

    private LayoutInflater mInflater;

    private LinearLayoutManager mLayoutManager;

    private DividerItemDecoration mItemDecoration;

    public RateListAdapter(Context context, HashMap<String, Rates> ratesHashMap) {
        mRatesHashMap = ratesHashMap;
        mInflater = LayoutInflater.from(context);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @NonNull
    @Override
    public RateHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View item = mInflater.inflate(RESOURCE, viewGroup, false);
        RateHolder holder = new RateHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RateHolder holder, final int i) {
        mKeys = mRatesHashMap.keySet().toArray(new String[mRatesHashMap.size()]);
        String key = mKeys[i];
        Rates rate = mRatesHashMap.get(key);
        holder.tvValue.setText(CurrencyFormatter.format(CurrencyFormatter.TRY, rate.getTry()));
        holder.tvUnit.setText(key);
    }

    @Override
    public int getItemCount() {
        return mRatesHashMap.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(mItemDecoration);
    }
}
