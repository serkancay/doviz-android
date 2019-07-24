package com.serkancay.doviz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import butterknife.BindView;
import com.serkancay.doviz.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindEvents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener);
    }

    private OnNavigationItemSelectedListener mNavigationItemSelectedListener
            = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.miExchangeRates: {
                    return true;
                }
                case R.id.miSettings: {
                    return true;
                }
            }
            return false;
        }
    };
}
