package com.tj.chaersi.nfccheck.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 小区定位
 * @deprecated
 */
public class CommunityLocalActivity extends BaseActivity {

    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.localAreaRecycler) RecyclerView localAreaRecycler;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_community_local);
        ButterKnife.bind(this);
        title.setText("小区定位");
    }

    @OnClick({R.id.leftBtn, R.id.reloadLayout})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.reloadLayout:
                break;
        }
    }

    private void loadLocalAreaReq(){

    }

    private void showDataLayout(boolean isShow){
        if(isShow){
            localAreaRecycler.setVisibility(View.VISIBLE);
            reloadLayout.setVisibility(View.GONE);
        }else{
            localAreaRecycler.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }
}
