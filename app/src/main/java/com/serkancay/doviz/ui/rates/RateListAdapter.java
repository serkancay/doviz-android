package com.serkancay.doviz.ui.rates;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.network.model.Rate;
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

        @BindView(R.id.llRootButton)
        LinearLayout llRootButton;

        RateHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final int RESOURCE = R.layout.item_rate_list;

    private HashMap<String, Rate> mRatesHashMap;

    private String[] mKeys;

    private LayoutInflater mInflater;

    private LinearLayoutManager mLayoutManager;

    private DividerItemDecoration mItemDecoration;

    private Callback mCallback;

    public RateListAdapter(Context context, HashMap<String, Rate> ratesHashMap) {
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
        final String key = mKeys[i];
        Rate rate = mRatesHashMap.get(key);
        holder.tvValue.setText(CurrencyFormatter.format(CurrencyFormatter.TRY, rate.getTry()));
        holder.tvUnit.setText(key);
        holder.llRootButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mCallback != null) {
                    mCallback.onItemClicked(key);
                }
            }
        });
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

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback{
        void onItemClicked(String base);
    }
}
