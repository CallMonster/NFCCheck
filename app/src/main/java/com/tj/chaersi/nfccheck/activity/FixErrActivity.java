package com.tj.chaersi.nfccheck.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.adapter.FixErrAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.FixErrModel;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 故障待办
 */
public class FixErrActivity extends BaseActivity {
    private String TAG="FixErrActivity";

    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.fixErrRecycler) RecyclerView fixErrRecycler;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private FixErrAdapter adapter;
    private List<FixErrModel.ListBean> fixErrArr;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err);
        ButterKnife.bind(this);
        title.setText("故障待办");

        showProgressDialog("加载中...");

        fixErrArr=new ArrayList<>();
        fixErrRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        fixErrRecycler.setLayoutManager(layoutManager);
        fixErrRecycler.addItemDecoration(new DividerDecoration(this));
        adapter = new FixErrAdapter(this, fixErrArr);
        fixErrRecycler.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                showTips("第" + position);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });


        loadFixErrData();
    }

    @OnClick({R.id.leftBtn, R.id.reloadLayout})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.reloadLayout:

                break;
        }
    }

    private void loadFixErrData(){
        Log.i(TAG,"id:"+BaseApplication.instance.user_id);
        OkHttpUtils.post().url(BaseConfigValue.FIXERR_URL)
                .addParams("userid", BaseApplication.instance.user_id)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                hideProgressDialog();

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"result:"+e);
                showDataLayout(false);
                showTips("服务器异常请稍后再试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"success result:"+response+"--"+id);
                if(response!=null){
                    FixErrModel fixErrModel=BaseApplication.gson.fromJson(response, FixErrModel.class);
                    if("1".equals(fixErrModel.getStatecode())){
                        fixErrArr.addAll(fixErrModel.getList());
                        adapter.notifyDataSetChanged();
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

    private void showDataLayout(boolean isShow){
        if(isShow){
            fixErrRecycler.setVisibility(View.VISIBLE);
            reloadLayout.setVisibility(View.GONE);
        }else{
            fixErrRecycler.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }

}
