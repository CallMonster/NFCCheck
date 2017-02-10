package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.Index01_01Adapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.Index01_01;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 巡检计划
 */
public class Index01Activity extends BaseActivity {
    private String TAG = "Index01_Activity";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.fixErrRecycler) RecyclerView fixErrRecycler;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private List<Index01_01.ListBean> residentArr;
    private Index01_01Adapter adapter;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_index01);
        ButterKnife.bind(this);
        title.setText("巡检计划");
        showProgressDialog("加载中..");

        residentArr = new ArrayList<>();
        fixErrRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        fixErrRecycler.setLayoutManager(layoutManager);
        fixErrRecycler.addItemDecoration(new DividerDecoration(this));
        adapter = new Index01_01Adapter(this, residentArr);
        fixErrRecycler.setAdapter(adapter);

        loadErrConfirmData();

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(Index01Activity.this, ResidentCheckActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("item", residentArr.get(position));
                intent.putExtras(mBundle);
                startActivityForResult(intent, 0);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
    }

    @OnClick({R.id.leftBtn, R.id.reloadLayout})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.reloadLayout:
                loadErrConfirmData();
                break;
        }
    }

    private void loadErrConfirmData() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKRESIDENT_PLAN_URL)
                .addParams("id", BaseApplication.instance.user_id)
                .build().execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "err:" + e);
                showDataLayout(false);
                showTips("服务器异常请稍后再试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, response);
                if (response != null) {
                    Index01_01 checkPlan = BaseApplication.gson.fromJson(response, Index01_01.class);
                    if ("1".equals(checkPlan.getStatecode())) {
                        residentArr.clear();
                        residentArr.addAll(checkPlan.getList());
                        adapter.notifyDataSetChanged();
                        showDataLayout(true);
                    } else {
                        showDataLayout(false);
                        showTips("服务器异常请稍后再试");
                    }
                } else {
                    showDataLayout(false);
                    showTips("服务器异常请稍后再试");
                }
            }
        });
    }

    private void showDataLayout(boolean isShow) {
        if (isShow) {
            fixErrRecycler.setVisibility(View.VISIBLE);
            reloadLayout.setVisibility(View.GONE);
        } else {
            fixErrRecycler.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }



}
