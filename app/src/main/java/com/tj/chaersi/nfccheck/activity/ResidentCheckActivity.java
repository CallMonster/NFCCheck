package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.adapter.ResidentCheckAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.db.Dao.ResidentCheckDao;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPlanModel;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;
import com.tj.chaersi.nfccheck.vo.Index01_01;
import com.tj.chaersi.nfccheck.vo.Resident01;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 居民巡检
 */
public class ResidentCheckActivity extends BaseActivity {
    private String TAG="ResidentCheckActivity";

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.positionBtn) CardView positionBtn;
    @BindView(R.id.pressureBtn) CardView pressureBtn;
    @BindView(R.id.igonreBtn) CardView igonreBtn;
    @BindView(R.id.errBtn) CardView errBtn;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private Index01_01.ListBean item01;

    private ArrayList<Resident01.ListBean> pointArr;
    private ResidentCheckAdapter adapter;
    private ResidentCheckDao residentDao;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_resident);
        ButterKnife.bind(this);
        titleView.setText("居民巡检");
        Intent intent=getIntent();
        item01 = (Index01_01.ListBean) intent.getSerializableExtra("item");
        showProgressDialog("加载中..");
        residentDao=new ResidentCheckDao(this);
        pointArr = new ArrayList<>();

        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        adapter = new ResidentCheckAdapter(this, pointArr);
        checkpointView.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                showTips("已选择"+pointArr.get(position).getName());
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });

        checkResidentRequest();
    }

    @OnClick({R.id.leftBtn, R.id.positionBtn, R.id.pressureBtn, R.id.igonreBtn, R.id.errBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.positionBtn:
                Resident01.ListBean chooseItem=adapter.getChooseItem();
                if(chooseItem==null){
                    showTips("请选择需要定位的小区后再试");
                }else{
                    Intent localIntent=new Intent(this,Index0101Act.class);
                    Bundle localBundle=new Bundle();
                    localBundle.putSerializable("routeItem",item01);
                    localBundle.putSerializable("areaItem",chooseItem);
                    localIntent.putExtras(localBundle);
                    startActivity(localIntent);
                }
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

    private void checkResidentRequest() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKRESIDENT_URL)
                .addParams("id", item01.getId())
                .addParams("time", item01.getPlantime())
                .build().execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"异常："+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"succ："+response);
                if (response != null) {
                    Resident01 resident01 = BaseApplication.gson.fromJson(response, Resident01.class);
                    if("1".equals(resident01.getStatecode())){
                        adapter.notifyItemChoosed(resident01.getList());
                        residentDao.clearDb();
                        residentDao.insertAllBill(resident01.getList());
                        showDataLayout(true);
                    }else{
                        showDataLayout(false);
                        showTips("服务器异常请稍后再试");
                    }
                }else{
                    showDataLayout(false);
                    showTips("服务器异常请稍后再试");
                }
            }
        });
    }

    private void showDataLayout(boolean isShow) {
        if (isShow) {
            checkpointView.setVisibility(View.VISIBLE);
            reloadLayout.setVisibility(View.GONE);
        } else {
            checkpointView.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }

}
