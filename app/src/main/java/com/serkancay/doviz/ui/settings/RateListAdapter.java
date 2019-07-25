package com.serkancay.doviz.ui.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.serkancay.doviz.R;
import com.serkancay.doviz.data.db.entity.Rate;
import com.serkancay.doviz.ui.settings.RateListAdapter.RateHolder;
import com.serkancay.doviz.util.CurrencyFormatter;
import com.serkancay.doviz.util.keystore.CryptorUtil;
import java.util.List;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public class RateListAdapter extends Adapter<RateHolder> {

    static class RateHolder extends ViewHolder {

        @BindView(R.id.llRootButton)
        LinearLayout llRootButton;

        @BindView(R.id.tvDate)
        TextView tvDate;

        @BindView(R.id.tvUnit)
        TextView tvUnit;

        @BindView(R.id.tvValue)
        TextView tvValue;

        RateHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final int RESOURCE = R.layout.item_settings_rate_list;

    private List<Rate> mRates;

    private LayoutInflater mInflater;

    private LinearLayoutManager mLayoutManager;

    private DividerItemDecoration mItemDecoration;

    private Context mContext;

    public RateListAdapter(Context context, List<Rate> rates) {
        mContext = context;
        mRates = rates;
        mInflater = LayoutInflater.from(context);
        mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        mItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @NonNull
    @Override
    public RateHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View item = mInflater.inflate(RESOURCE, parent, false);
        RateHolder holder = new RateHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RateHolder holder, final int position) {
        Rate rate = mRates.get(position);
        String value = CurrencyFormatter
                .format(CurrencyFormatter.TRY, Float.parseFloat(CryptorUtil.decrypt(mContext, rate.value)));
        holder.tvDate.setText(CryptorUtil.decrypt(mContext, rate.date));
        holder.tvUnit.setText(CryptorUtil.decrypt(mContext, rate.base));
        holder.tvValue.setText(value);
    }

    @Override
    public int getItemCount() {
        return mRates.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(mItemDecoration);
    }
}
