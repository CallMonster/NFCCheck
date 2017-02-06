package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 居民巡检
 */
public class ResidentCheckActivity extends BaseActivity {

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.positionBtn) CardView positionBtn;
    @BindView(R.id.pressureBtn) CardView pressureBtn;
    @BindView(R.id.igonreBtn) CardView igonreBtn;
    @BindView(R.id.errBtn) CardView errBtn;

    private ArrayList<CheckPointModel.ListBean> pointArr;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_resident);
        ButterKnife.bind(this);
        titleView.setText("居民巡检");

        pointArr = new ArrayList<>();
        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        CheckPointAdapter adapter = new CheckPointAdapter(this, pointArr);
        checkpointView.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                showTips("第" + position);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
    }

    private void checkResidentRequest() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKRESIDENT_URL);
    }

    @OnClick({R.id.leftBtn, R.id.positionBtn, R.id.pressureBtn, R.id.igonreBtn, R.id.errBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.positionBtn:
                Intent localIntent=new Intent(this,CommunityLocalActivity.class);
                Bundle localBundle=new Bundle();
                localBundle.putString("type","local");
                localIntent.putExtras(localBundle);
                startActivity(localIntent);
                break;
            case R.id.pressureBtn:
                Intent pressureIntent=new Intent(this,CommunityLocalActivity.class);
                Bundle pressureBundle=new Bundle();
                pressureBundle.putString("type","pressure");
                pressureIntent.putExtras(pressureBundle);
                startActivity(pressureIntent);
                break;
            case R.id.igonreBtn:
                break;
            case R.id.errBtn:
                break;
        }
    }
}
