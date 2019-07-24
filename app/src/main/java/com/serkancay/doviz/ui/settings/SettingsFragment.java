package com.serkancay.doviz.ui.settings;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import butterknife.BindView;
import com.serkancay.doviz.R;
import com.serkancay.doviz.helper.LocaleHelper;
import com.serkancay.doviz.ui.base.BaseFragment;
import java.util.LinkedHashMap;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class SettingsFragment extends BaseFragment implements SettingsView, OnItemSelectedListener {

    @BindView(R.id.spinner)
    Spinner spinner;

    private SettingsPresenter mPresenter;

    private LinkedHashMapAdapter mLinkedHashMapAdapter;

    private LinkedHashMap<String, String> mLanguages;

    private int mSpinnerCheck = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void onCreated() {
        mLanguages = new LinkedHashMap<>();
        mLinkedHashMapAdapter = new LinkedHashMapAdapter(context, android.R.layout.simple_spinner_item, mLanguages);
        mLinkedHashMapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mLinkedHashMapAdapter);
        mPresenter = new SettingsPresenter(context, this);
    }

    @Override
    public void onResumed() {
        getNavigationPresenter().setTitle(getString(R.string.settings_fragment_title));
        getNavigationPresenter().setDisplayHomeAsUpEnabled(false);
        mPresenter.onResume();
    }

    @Override
    public void bindEvents() {
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (++mSpinnerCheck > 1) {
            String langKey = mLinkedHashMapAdapter.getItem(position).getKey().toString();
            LocaleHelper.setLocale(context, langKey);
            mPresenter.restartApp();
        }
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {

    }

    @Override
    public void updateSpinner(final LinkedHashMap<String, String> languages) {
        mLanguages.clear();
        mLanguages.putAll(languages);
        mLinkedHashMapAdapter.notifyDataSetChanged();
    }
}
