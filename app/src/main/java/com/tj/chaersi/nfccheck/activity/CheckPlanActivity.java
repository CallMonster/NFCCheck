package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.adapter.CheckPlanAdapter;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPlanModel;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public class CheckPlanActivity extends BaseActivity {
    private String TAG="CheckPlanActivity";

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.planCheckRecycler) RecyclerView planRecyclerView;
    @BindView(R.id.reloadLayout)LinearLayout reloadLayout;

    private List<CheckPlanModel.ListBean> checkArr;
    private CheckPlanAdapter adapter;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_check_plan);
        ButterKnife.bind(this);
        titleView.setText("巡检计划");
        leftBtn.setOnClickListener(this);

        showProgressDialog("读取中ing...");

        checkArr=new ArrayList<>();
        planRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CheckPlanActivity.this);
        planRecyclerView.setLayoutManager(layoutManager);
        planRecyclerView.addItemDecoration(new DividerDecoration(CheckPlanActivity.this));
        adapter=new CheckPlanAdapter(CheckPlanActivity.this,checkArr);
        planRecyclerView.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                if ("1".equals(checkArr.get(position).getChecktype())) {
                    Intent smartNFCIntent = new Intent(CheckPlanActivity.this, SmartCheck_NFCActivity.class);
                    smartNFCIntent.putExtra("detail_id",checkArr.get(position).getId());
                    startActivity(smartNFCIntent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                } else {
                    Intent smartGPGIntent = new Intent(CheckPlanActivity.this, SmartCheck_GPSActivity.class);
                    smartGPGIntent.putExtra("detail_id",checkArr.get(position).getId());
                    startActivity(smartGPGIntent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }
            }
            @Override
            public void onItemViewClickListener(int position) {

            }
        });
        checkPlanRequest();
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

    private void checkPlanRequest(){
        OkHttpUtils.post().url(BaseConfigValue.CHECKPLAN_URL)
                .addParams("id", BaseApplication.instance.user_id)
                .build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"error:"+e);
                reloadLayout.setVisibility(View.VISIBLE);
                planRecyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"succ:"+response);
                if(response!=null) {
                    CheckPlanModel checkPlan = BaseApplication.gson.fromJson(response, CheckPlanModel.class);
                    if("1".equals(checkPlan.getStatecode())){
                        checkArr.addAll(checkPlan.getList());
                        adapter.notifyDataSetChanged();

                        planRecyclerView.setVisibility(View.VISIBLE);
                        reloadLayout.setVisibility(View.GONE);
                    }else{
                        showTips("服务器异常，请稍后再试");
                        planRecyclerView.setVisibility(View.GONE);
                        reloadLayout.setVisibility(View.VISIBLE);
                    }
                }else{
                    showTips("服务器异常，请稍后再试");
                    planRecyclerView.setVisibility(View.GONE);
                    reloadLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
