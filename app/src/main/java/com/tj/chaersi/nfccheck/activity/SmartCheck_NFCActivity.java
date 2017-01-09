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
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 智能巡检_nfc
 */
public class SmartCheck_NFCActivity extends BaseActivity {
    private String TAG="SmartCheck_NFCActivity";

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isFirst = true;

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
        titleView.setText("NFC巡检");
        leftBtn.setOnClickListener(this);

        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        CheckPointAdapter adapter=new CheckPointAdapter(this,getCheckPointArr());
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

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[] { ndef };// 过滤器
        mTechLists = new String[][] {
                new String[] { MifareClassic.class.getName() },
                new String[] { NfcA.class.getName() } };// 允许扫描的标签类型
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

    String[] nameStr = new String[]{
            "兵车工厂", "训练兵营", "科技大厦",
            "作战实验室", "天气控制器", "蒸汽发电工厂", "油料库"
    };
    String[] workerStr = new String[]{
            "艾泽拉斯", "葛二蛋", "赵二虎",
            "井文政", "姜舞阳", "文泽地", "熊亲望"
    };
    private ArrayList<HashMap<String,String>> getCheckPointArr(){
        ArrayList<HashMap<String,String>> itemArr=new ArrayList<>();
        for(int i=0;i<20;i++){
            HashMap<String,String> item=new HashMap<>();
            item.put("serial",(i+1)+"");
            item.put("name",nameStr[i%7]);
            item.put("state",(i%4==0?1:0)+"");
            item.put("time","12/28");
            item.put("worker",workerStr[i%7]);
            itemArr.add(item);
        }
        return itemArr;
    }

    /** NFC读取功能 */
    @Override
    protected void onResume() {
        super.onResume();
        if(nfcAdapter!=null){
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
            titleView.setText(result);
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
