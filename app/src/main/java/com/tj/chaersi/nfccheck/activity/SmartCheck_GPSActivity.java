package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public class SmartCheck_GPSActivity extends BaseActivity {

    private String TAG = "SmartCheck_GPSActivity";

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftBtn) View leftBtn;

    @BindView(R.id.topBtnLayout) LinearLayout topBtnLayout;
    @BindView(R.id.middleLine1) View middleLine1;
    @BindView(R.id.middleLayout) LinearLayout middleLayout;
    @BindView(R.id.middleLine2) View middleLine2;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private String checkid;
    private ArrayList<CheckPointModel.ListBean> pointArr;
    private CheckPointAdapter adapter;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_smart_check_gps);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        checkid = intent.getStringExtra("detail_id");

        showProgressDialog("加载中...");
        titleView.setText("GPS巡检");
        leftBtn.setOnClickListener(this);
        checkPointRequest();

        pointArr=new ArrayList<>();
        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        adapter=new CheckPointAdapter(this,pointArr);
        checkpointView.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                showTips("第"+position);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });

        reloadLayout.setOnClickListener(this);
    }

    @Override
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.reloadLayout:
                showProgressDialog("重新加载ing");
                checkPointRequest();
                break;
        }
    }

    private void checkPointRequest() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKPOINT_URL)
                .addParams("id", checkid)
                .build().execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "error:" + e);
                showDataLayout(false);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, "succ:" + response);
                if (response != null) {
                    CheckPointModel checkpoint = BaseApplication.instance.gson.fromJson(response, CheckPointModel.class);
                    if ("1".equals(checkpoint.getStatecode())) {
                        adapter.notifyItemChoosed(checkpoint.getList());
                        showDataLayout(true);
                    } else {
                        showTips("服务器异常，请稍后再试");
                        showDataLayout(false);
                    }
                } else {
                    showTips("服务器异常，请稍后再试");
                    showDataLayout(false);
                }
            }
        });
    }

    private void showDataLayout(boolean isShow){
        if(isShow){
            topBtnLayout.setVisibility(View.VISIBLE);
            middleLine1.setVisibility(View.VISIBLE);
            middleLayout.setVisibility(View.VISIBLE);
            middleLine2.setVisibility(View.VISIBLE);
            checkpointView.setVisibility(View.VISIBLE);
            reloadLayout.setVisibility(View.GONE);
        }else{
            topBtnLayout.setVisibility(View.GONE);
            middleLine1.setVisibility(View.GONE);
            middleLayout.setVisibility(View.GONE);
            middleLine2.setVisibility(View.GONE);
            checkpointView.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }

}
