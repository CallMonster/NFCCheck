package com.tj.chaersi.nfccheck.activity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 故障上报
 */
public class ErrorUploadActivity extends BaseActivity {

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.errArea) Spinner errArea;
    @BindView(R.id.leftBtn) View leftBtn;


    @Override
    public void onCreate() {
        setContentView(R.layout.activity_error_upload);
        ButterKnife.bind(this);
        titleView.setText("故障上报");
        leftBtn.setOnClickListener(this);

        String[] titleData = getResources().getStringArray(R.array.errAreaArr);
        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(this, R.layout.item_errarea, titleData);
        errArea.setAdapter(titleAdapter);
    }

    @Override
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }
}
