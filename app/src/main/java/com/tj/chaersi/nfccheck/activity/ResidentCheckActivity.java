package com.tj.chaersi.nfccheck.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * 居民巡检
 */
public class ResidentCheckActivity extends BaseActivity {

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.checkpointView) RecyclerView checkpointView;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_resident);
        ButterKnife.bind(this);
        titleView.setText("居民巡检");

        checkpointView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        checkpointView.setLayoutManager(layoutManager);
        checkpointView.addItemDecoration(new DividerDecoration(this));
        CheckPointAdapter adapter=new CheckPointAdapter(this,null);
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

    }

    @Override
    public void onClickListener(View v) {

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


}
