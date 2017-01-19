package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.vo.FixErrModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixErrFinishActivity extends BaseActivity {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.imgABtn) ImageView imgABtn;
    @BindView(R.id.imgBBtn) ImageView imgBBtn;
    @BindView(R.id.imgCBtn) ImageView imgCBtn;
    @BindView(R.id.uploadBtn) Button uploadBtn;

    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixUser) TextView fixUser;
    @BindView(R.id.remarkEdit) EditText remarkEdit;

    private FixErrModel.ListBean fixErrItem;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err_finish);
        ButterKnife.bind(this);
        title.setText("维修完毕");
        Intent intent = getIntent();
        fixErrItem = (FixErrModel.ListBean) intent.getSerializableExtra("item");

        fixname.setText(fixErrItem.getName());
        fixtime.setText(fixErrItem.getOverTime());
        fixUser.setText(fixErrItem.getSendUserName());

    }

    @OnClick({R.id.leftBtn, R.id.imgABtn, R.id.imgBBtn, R.id.imgCBtn, R.id.uploadBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.imgABtn:
                break;
            case R.id.imgBBtn:
                break;
            case R.id.imgCBtn:
                break;
            case R.id.uploadBtn:
                break;
        }
    }
}
