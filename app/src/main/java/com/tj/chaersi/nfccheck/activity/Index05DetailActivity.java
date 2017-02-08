package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.FixErrModel;
import com.tj.chaersi.nfccheck.vo.Index05Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Index05DetailActivity extends BaseActivity {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixdetail) TextView fixdetail;
    @BindView(R.id.fixContact) TextView fixContact;
    @BindView(R.id.fixImage_A) ImageView fixImageA;
    @BindView(R.id.fixImage_B) ImageView fixImageB;
    @BindView(R.id.fixImage_C) ImageView fixImageC;
    @BindView(R.id.dealWorker) TextView dealWorker;

    private Index05Result.ListBean itemDetail;
    private PreferenceUtils preference;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_index05_detail);
        ButterKnife.bind(this);
        preference=new PreferenceUtils(this);
        title.setText("故障确认详情");

        Intent intent = getIntent();
        itemDetail = (Index05Result.ListBean) intent.getSerializableExtra("item");

        fixname.setText(TextUtils.isEmpty(itemDetail.getName())?"-":itemDetail.getName());
        fixtime.setText(TextUtils.isEmpty(itemDetail.getServiceTime())?"-":itemDetail.getServiceTime());
        fixdetail.setText(TextUtils.isEmpty(itemDetail.getInfo())?"-":itemDetail.getInfo());
        fixContact.setText(TextUtils.isEmpty(itemDetail.getSendCellphone()+" "+itemDetail.getSendUserName())
                ?"-":itemDetail.getSendCellphone()+" "+itemDetail.getSendUserName());

        ArrayList<ImageView> imageViews=new ArrayList<>();
        imageViews.add(fixImageA);
        imageViews.add(fixImageB);
        imageViews.add(fixImageC);
        for(int i=0;i<itemDetail.getUrls().size();i++){
            Glide.with(this).load(BaseConfigValue.IMAGE_URL+itemDetail.getUrls().get(i))
                    .into(imageViews.get(i));
        }
        dealWorker.setText(preference.getUserInfo().getRealname());
    }

    @OnClick({R.id.leftBtn, R.id.fixFinishBtn, R.id.fixBackBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.fixFinishBtn:
                Intent finishIntent=new Intent(this,Index5DetailOverAct.class);
                Bundle finishBundle=new Bundle();
                finishBundle.putSerializable("detail",itemDetail);
                finishIntent.putExtras(finishBundle);
                startActivityForResult(finishIntent,0);
                break;
            case R.id.fixBackBtn:
                Intent backIntent=new Intent(this,Index5DetailBackAct.class);
                Bundle backBundle=new Bundle();
                backBundle.putSerializable("detail",itemDetail);
                backIntent.putExtras(backBundle);
                startActivityForResult(backIntent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==0){
            setResult(RESULT_OK);
            finish();
        }
    }
}
