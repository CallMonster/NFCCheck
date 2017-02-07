package com.tj.chaersi.nfccheck.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.DateUtils;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.db.Dao.NFCCheckPointDao;
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

/**
 * 智能巡检_nfc
 */
public class SmartCheck_NFCActivity extends BaseActivity {
    private String TAG = "SmartCheck_NFCActivity";
    private static int CHECK_POINT_CODE=1;

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;

    @BindView(R.id.topBtnLayout) LinearLayout topBtnLayout;
    @BindView(R.id.middleLine1) View middleLine1;
    @BindView(R.id.middleLayout) LinearLayout middleLayout;
    @BindView(R.id.middleLine2) View middleLine2;
    @BindView(R.id.reloadLayout) LinearLayout reloadLayout;

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isFirst = true;

    private PreferenceUtils preference;

    private ArrayList<CheckPointModel.ListBean> pointArr;
    private CheckPointAdapter adapter;
    private String checkid,planTime;
    private NFCCheckPointDao pointDao;
    @Override
    public void onCreate() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            showTips(getResources().getString(R.string.no_nfc));
            finish();
            return;
        } else if (!nfcAdapter.isEnabled()) {
            showTips(getResources().getString(R.string.open_nfc));
            finish();
            return;
        }
        setContentView(R.layout.activity_smart_check_nfc);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        checkid = intent.getStringExtra("detail_id");
        planTime = intent.getStringExtra("plantime");

        titleView.setText("NFC巡检");
        leftBtn.setOnClickListener(this);

        preference=new PreferenceUtils(this);

        pointArr = new ArrayList<>();
        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        adapter = new CheckPointAdapter(this, pointArr);
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
        pointDao=new NFCCheckPointDao(this);

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[]{ndef};// 过滤器
        mTechLists = new String[][]{
                new String[]{MifareClassic.class.getName()},
                new String[]{NfcA.class.getName()}};// 允许扫描的标签类型

        if(TextUtils.isEmpty(checkid)){
            showTips("请正常打开APP后再试");
            hideProgressDialog();
        }else{
            checkPointRequest();
        }
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
                if(TextUtils.isEmpty(checkid)){
                    showTips("请正常打开APP后再试");
                }else{
                    showProgressDialog("重新加载ing");
                    checkPointRequest();
                }
                break;
        }
    }

    private void checkPointRequest() {
        OkHttpUtils.post().url(BaseConfigValue.CHECKPOINT_URL)
                .addParams("id", checkid)
                .build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
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
                        pointDao.clearDb();
                        pointDao.insertAllBill(checkpoint.getList());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==CHECK_POINT_CODE){
            showProgressDialog("数据更新中..");
            checkPointRequest();
        }
    }

    /**
     * NFC读取功能
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mFilters, mTechLists);
        }
        if (isFirst) {
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())) {
                String result = processIntent(getIntent());
                /** result即NFC中读取的数据*/
                titleView.setText(result);
            }
            isFirst = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            String result = processIntent(intent);
            /** result即NFC中读取的数据*/
            CheckPointModel.ListBean item= pointDao.selectNFCItem(result);
            if(item!=null){
                if("未巡检".equals(item.getState().trim())){
                    Intent detailIntent=new Intent(this,CheckPointDetailActivity.class);
                    Bundle mBundle=new Bundle();
                    mBundle.putString("name",item.getName());
                    mBundle.putString("routeId",item.getId());
                    mBundle.putString("userId",BaseApplication.instance.user_id);
                    mBundle.putString("userName",preference.getUserInfo().getRealname());
                    mBundle.putString("planTime",planTime);
                    mBundle.putString("pointid",item.getId());
                    mBundle.putString("checktime",DateUtils.convertTime(item.getCreateDate()));
                    detailIntent.putExtras(mBundle);
                    startActivityForResult(detailIntent,CHECK_POINT_CODE);
                }else{
                    showTips("此巡检点已巡检");
                }
            }else{
                showTips("未找到匹配数据");
            }
        }
    }

    /**
     * 获取tab标签中的内容
     *
     * @param intent
     * @return
     */
    @SuppressLint("NewApi")
    private String processIntent(Intent intent) {
        Parcelable[] rawmsgs = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawmsgs[0];
        NdefRecord[] records = msg.getRecords();
        String resultStr = new String(records[0].getPayload());
        return resultStr;
    }

}
