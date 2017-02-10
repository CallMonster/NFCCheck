package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.DateUtils;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.adapter.Index01ResidentAdapter;
import com.tj.chaersi.nfccheck.adapter.Index01_01Adapter;
import com.tj.chaersi.nfccheck.adapter.ResidentLocalAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.db.Dao.ResidentDAO;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.Index01ResidentModel;
import com.tj.chaersi.nfccheck.vo.Index01_01;
import com.tj.chaersi.nfccheck.vo.Index_LocalModel;
import com.tj.chaersi.nfccheck.vo.Resident01;
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
 * 选择居民户
 */
public class Index01RsidentAct extends BaseActivity {
    private String TAG="Index01RsidentAct";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.searchEdit) EditText searchEdit;
    @BindView(R.id.fixErrRecycler) RecyclerView fixErrRecycler;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private Index01_01.ListBean item01;
    private Resident01.ListBean item02;
    private Index_LocalModel.ListBean item03;

    private List<Index01ResidentModel.ListBean> fixErrArr;
    private Index01ResidentAdapter adapter;

    private ResidentDAO residentDao;
    private PreferenceUtils preference;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_index01_rsident);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        item01= (Index01_01.ListBean) intent.getSerializableExtra("routeItem");
        item02= (Resident01.ListBean) intent.getSerializableExtra("areaItem");
        item03= (Index_LocalModel.ListBean) intent.getSerializableExtra("localItem");

        title.setText("选择居民户");
        showProgressDialog("加载中..");

        residentDao=new ResidentDAO(this);
        preference=new PreferenceUtils(this);

        fixErrArr=new ArrayList<>();
        fixErrRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        fixErrRecycler.setLayoutManager(layoutManager);
        fixErrRecycler.addItemDecoration(new DividerDecoration(this));
        adapter = new Index01ResidentAdapter(this, fixErrArr);
        fixErrRecycler.setAdapter(adapter);

        loadChooseResidentData();

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(Index01RsidentAct.this,CheckPointDetailActivity.class);
                Bundle mBundle=new Bundle();
                mBundle.putString("routeId",item02.getId());
                mBundle.putString("name",item02.getName());
                mBundle.putString("userId",BaseApplication.instance.user_id);
                mBundle.putString("userName",preference.getUserInfo().getRealname());
                mBundle.putString("planTime",item02.getPlantime());
                mBundle.putString("pointid",item02.getId());
                mBundle.putString("checktime", DateUtils.convertTime(item02.getCreateDate()));
                intent.putExtras(mBundle);
                startActivityForResult(intent,0);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
    }

    @OnClick({R.id.leftBtn, R.id.searchBtn, R.id.reloadLayout})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.searchBtn:
                String searchStr=searchEdit.getText().toString().trim();
                if(TextUtils.isEmpty(searchStr)){
                    showTips("请输入搜索内容后再试");
                }else{
                    List<Index01ResidentModel.ListBean> resultArr=residentDao.selectResident(searchStr);
                    if(resultArr!=null){
                        fixErrArr.clear();
                        fixErrArr.addAll(resultArr);
                        adapter.notifyDataSetChanged();
                    }else{
                        showTips("为查询到相关数据");
                    }
                }
                break;
            case R.id.reloadLayout:
                showProgressDialog("重新加载..");
                loadChooseResidentData();
                break;
        }
    }

    private void loadChooseResidentData() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKRESIDENT_CHOOSE_URL)
                .addParams("xqid", item03.getId())
                .build().execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"err:"+e);
                showDataLayout(false);
                showTips("服务器异常请稍后再试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"succ:"+response);
                if(response!=null){
                    Index01ResidentModel model=BaseApplication.gson.fromJson(response, Index01ResidentModel.class);
                    if("1".equals(model.getStatecode())){
                        fixErrArr.clear();
                        fixErrArr.addAll(model.getList());
                        adapter.notifyDataSetChanged();
                        residentDao.clearDb();
                        residentDao.insertAllBill(model.getList());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==0){
            showProgressDialog("数据更新中..");
            loadChooseResidentData();
        }
    }

}
