package com.tj.chaersi.nfccheck.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择居民户
 */
public class ChooseResidentActivity extends BaseActivity {

    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.localEdit) EditText localEdit;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_choose_resident);
        ButterKnife.bind(this);
        title.setText("选择居民户");
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.leftBtn:
                finish();
                break;
        }
    }



}
