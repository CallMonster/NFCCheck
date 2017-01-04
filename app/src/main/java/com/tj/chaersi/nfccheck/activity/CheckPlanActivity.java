package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckPlanAdapter;
import com.tj.chaersi.nfccheck.adapter.CheckPointAdapter;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckPlanActivity extends BaseActivity {

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.planCheckRecycler) RecyclerView planRecyclerView;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_check_plan);
        ButterKnife.bind(this);
        titleView.setText("巡检计划");
        leftBtn.setOnClickListener(this);

        planRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        planRecyclerView.setLayoutManager(layoutManager);
        planRecyclerView.addItemDecoration(new DividerDecoration(this));
        CheckPlanAdapter adapter=new CheckPlanAdapter(this,getCheckPointArr());
        planRecyclerView.setAdapter(adapter);

        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                if (position % 2 == 0) {
                    Intent smartGPGIntent = new Intent(CheckPlanActivity.this, SmartCheck_GPSActivity.class);
                    startActivity(smartGPGIntent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                } else {
                    Intent smartNFCIntent = new Intent(CheckPlanActivity.this, SmartCheck_NFCActivity.class);
                    startActivity(smartNFCIntent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }

            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
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
            "兵车工厂巡检", "训练兵营巡检", "科技大厦巡检",
            "作战实验室巡检", "天气控制器巡检", "蒸汽发电工厂巡检", "油料库巡检"
    };

    private ArrayList<HashMap<String, String>> getCheckPointArr() {
        ArrayList<HashMap<String, String>> itemArr = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", i + "");
            item.put("name", nameStr[i % 7]);
            itemArr.add(item);
        }
        return itemArr;
    }
}
