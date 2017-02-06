package com.tj.chaersi.nfccheck.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 小区压力
 */
public class PressureActivity extends BaseActivity {

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_pressure);
        ButterKnife.bind(this);
    }

    @Override
    public void onClickListener(View v) {

    }
}
