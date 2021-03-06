package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.DateUtils;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.FixErrModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixErrDetailActivity extends BaseActivity {

    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixdetail) TextView fixdetail;
    @BindView(R.id.fixContact) TextView fixContact;
    @BindView(R.id.fixImage_A) ImageView fixImageA;
    @BindView(R.id.fixImage_B) ImageView fixImageB;
    @BindView(R.id.fixImage_C) ImageView fixImageC;
    @BindView(R.id.fixFinishBtn) Button fixFinishBtn;
    @BindView(R.id.fixBackBtn) Button fixBackBtn;
    private FixErrModel.ListBean fixErrItem;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err_detail);
        ButterKnife.bind(this);
        title.setText("隐患详情");
        Intent intent = getIntent();
        fixErrItem = (FixErrModel.ListBean) intent.getSerializableExtra("item");

        fixname.setText(TextUtils.isEmpty(fixErrItem.getName())?"-":fixErrItem.getName());
        fixtime.setText(TextUtils.isEmpty(fixErrItem.getServiceTime())?"-":fixErrItem.getServiceTime());
        fixdetail.setText(TextUtils.isEmpty(fixErrItem.getInfo())?"-":fixErrItem.getInfo());
        fixContact.setText(TextUtils.isEmpty(fixErrItem.getSendCellphone()+" "+fixErrItem.getSendUserName())
                ?"-":fixErrItem.getSendCellphone()+" "+fixErrItem.getSendUserName());

        ArrayList<ImageView> imageViews=new ArrayList<>();
        imageViews.add(fixImageA);
        imageViews.add(fixImageB);
        imageViews.add(fixImageC);
        for(int i=0;i<fixErrItem.getUrls().size();i++){
            Glide.with(this).load(BaseConfigValue.IMAGE_URL+fixErrItem.getUrls().get(i))
                    .into(imageViews.get(i));
        }
    }

    @OnClick({R.id.leftBtn, R.id.fixFinishBtn, R.id.fixBackBtn})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.fixFinishBtn:
                Intent finishIntent=new Intent(this,FixErrFinishActivity.class);
                Bundle finishBundle=new Bundle();
                finishBundle.putSerializable("detail",fixErrItem);
                finishIntent.putExtras(finishBundle);
                startActivityForResult(finishIntent,0);
                break;
            case R.id.fixBackBtn:
                Intent backIntent=new Intent(this,FixErrBackActivity.class);
                Bundle backBundle=new Bundle();
                backBundle.putSerializable("detail",fixErrItem);
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
