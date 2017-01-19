package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.vo.FixErrModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixErrBackActivity extends BaseActivity {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixUser) TextView fixUser;
    @BindView(R.id.remarkEdit) EditText remarkEdit;
    @BindView(R.id.uploadBtn) Button uploadBtn;

    private FixErrModel.ListBean fixErrItem;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err_back);
        ButterKnife.bind(this);
        title.setText("隐患退回");
        Intent intent = getIntent();
        fixErrItem = (FixErrModel.ListBean) intent.getSerializableExtra("item");

        fixname.setText(fixErrItem.getName());
        fixtime.setText(fixErrItem.getOverTime());
        fixUser.setText(fixErrItem.getSendUserName());

    }

    @OnClick(R.id.uploadBtn)
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.uploadBtn:

                break;
        }
    }
}
