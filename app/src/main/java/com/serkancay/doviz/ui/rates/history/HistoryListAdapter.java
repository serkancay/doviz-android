package com.serkancay.doviz.ui.rates.history;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.network.model.Rate;
import com.serkancay.doviz.ui.rates.history.HistoryListAdapter.HistoryHolder;
import com.serkancay.doviz.util.CurrencyFormatter;
import java.util.HashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class HistoryListAdapter extends Adapter<HistoryHolder> {

    static class HistoryHolder extends ViewHolder {

        @BindView(R.id.tvDate)
        TextView tvDate;

        @BindView(R.id.tvValue)
        TextView tvValue;

        HistoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final int RESOURCE = R.layout.item_history_list;

    private HashMap<String, Rate> mRatesHashMap;

    private String[] mKeys;

    private LayoutInflater mInflater;

    private LinearLayoutManager mLayoutManager;

    private DividerItemDecoration mItemDecoration;

    public HistoryListAdapter(Context context, HashMap<String, Rate> ratesHashMap) {
        mRatesHashMap = ratesHashMap;
        mInflater = LayoutInflater.from(context);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View item = mInflater.inflate(RESOURCE, viewGroup, false);
        HistoryHolder holder = new HistoryHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryHolder holder, final int i) {
        mKeys = mRatesHashMap.keySet().toArray(new String[mRatesHashMap.size()]);
        final String key = mKeys[i];
        Rate rate = mRatesHashMap.get(key);
        holder.tvValue.setText(CurrencyFormatter.format(CurrencyFormatter.TRY, rate.getTry()));
        holder.tvDate.setText(key);
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
