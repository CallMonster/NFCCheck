package com.tj.chaersi.nfccheck.activity;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Data;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Judge;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Photo;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnAdapterListener;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.nfccheck.vo.PointDetailModel;
import com.tj.chaersi.nfccheck.widget.ListViewShowView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.opensrc.selectphoto.SystemAlbumPickerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 故障详情
 */
public class CheckPointDetailActivity extends BaseActivity {
    private String TAG="CheckPointDetailActivity";

    @BindView(R.id.leftView) ImageView leftView;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.problemLayout) LinearLayout problemLayout;

    @BindView(R.id.checkUserName) TextView checkUserName;
    @BindView(R.id.checkTime) TextView checkTime;
    @BindView(R.id.parentLayout) LinearLayout parentLayout;
    @BindView(R.id.saveBtn) Button saveBtn;

    private String detailId="f5b737b2-ef22-4ead-b560-5ef56405fd06";
    private ArrayList<PointDetailModel.ListBean> dataArr;
    private ArrayList<PointDetailModel.ListBean> judgeArr;
    private ArrayList<PointDetailModel.ListBean> photoArr;

    private CheckDetailAdapter_Data adapterData;//填入数据的适配器
    private CheckDetailAdapter_Judge adapterJudge;//正常与不正常的适配器
    private CheckDetailAdapter_Photo adapterPhoto;//照片选择的适配器
    private int TEMP_POSITION_CODE;
    private String TEMP_TAG;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_error_detail);
        ButterKnife.bind(this);
//        Intent intent=getIntent();
//        detailId=intent.getStringExtra("detailId");

        title.setText("巡检点详情");
        leftBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        pointDetailReq();
    }

    @Override
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.leftBtn:
                finish();
//                if(adaptersArr.get(0).getEditArr().size()>0){
//                    Log.i(TAG,adaptersArr.get(0).getEditArr().get(0).get("position")
//                            +"-----"+adaptersArr.get(0).getEditArr().get(0).get("editValue"));
//                }
                break;
            case R.id.saveBtn:
                Log.i(TAG,"addr:"+adapterPhoto.getItemAArr().get(0).get("imgpath"));
                uploadImageReq(adapterPhoto.getItemAArr().get(0).get("imgpath"));
                break;
        }
    }

    private void pointDetailReq() {
        OkHttpUtils.post().url(BaseConfigValue.POINTDETAIL_URL)
                .addParams("id", detailId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"err result:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"result:"+response);
                if(response!=null) {
                    PointDetailModel detailModel = BaseApplication.gson.fromJson(response, PointDetailModel.class);
                    if ("1".equals(detailModel.getStatecode())) {
//                        checkUserName.setText(detailModel.g);
//                        checkTime.setText();
                        List<PointDetailModel.ListBean> listbeanArr=detailModel.getList();

                        photoArr=new ArrayList<PointDetailModel.ListBean>();
                        judgeArr=new ArrayList<PointDetailModel.ListBean>();
                        dataArr=new ArrayList<PointDetailModel.ListBean>();

                        for(PointDetailModel.ListBean item:listbeanArr){
                            if("2".equals(item.getEliminateType())){
                                photoArr.add(item);
                            }else if("3".equals(item.getEliminateType())){
                                judgeArr.add(item);
                            }else if("1".equals(item.getEliminateType())){
                                dataArr.add(item);
                            }
                        }

                        if (dataArr.size() > 0) {
                            LinearLayout dataView = (LinearLayout) getLayoutInflater().inflate(R.layout.type_text_item, null);
                            ListViewShowView textListView = (ListViewShowView) dataView.findViewById(R.id.textListView);
                            adapterData = new CheckDetailAdapter_Data(CheckPointDetailActivity.this, dataArr);
                            textListView.setAdapter(adapterData);
                            problemLayout.addView(dataView);
                        }

                        if (judgeArr.size() > 0) {
                            LinearLayout chooseView = (LinearLayout) getLayoutInflater().inflate(R.layout.type_text_item, null);
                            ListViewShowView textListView = (ListViewShowView) chooseView.findViewById(R.id.textListView);
                            adapterJudge = new CheckDetailAdapter_Judge(CheckPointDetailActivity.this, judgeArr);
                            textListView.setAdapter(adapterJudge);
                            problemLayout.addView(chooseView);
                        }

                        if (photoArr.size() > 0) {
                            LinearLayout photoView = (LinearLayout) getLayoutInflater().inflate(R.layout.type_text_item, null);
                            ListViewShowView textListView = (ListViewShowView) photoView.findViewById(R.id.textListView);
                            adapterPhoto = new CheckDetailAdapter_Photo(CheckPointDetailActivity.this, photoArr);
                            textListView.setAdapter(adapterPhoto);
                            problemLayout.addView(photoView);
                            adapterPhoto.addItemViewClickListener(new OnAdapterListener() {
                                @Override
                                public void onItemViewClickListener(int position, String TAG) {
                                    TEMP_POSITION_CODE=position;
                                    TEMP_TAG=TAG;
                                    Intent intent = new Intent(CheckPointDetailActivity.this, SystemAlbumPickerActivity.class);
                                    intent.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                                    startActivityForResult(intent, position);
                                }
                            });
                        }
                    }else{
                        showTips("服务器异常，请稍后再试");
                    }
                }else{
                    showTips("服务器异常，请稍后再试");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEMP_POSITION_CODE && resultCode == SystemAlbumPickerActivity.resultCode_SINGLE_PATH) {
            if(TEMP_POSITION_CODE!=-1&&TEMP_TAG!=null){
                String imgPath=data.getStringExtra(SystemAlbumPickerActivity.key_singlePath);
                adapterPhoto.setImagePath(TEMP_POSITION_CODE,TEMP_TAG,imgPath);
                TEMP_POSITION_CODE=-1;
                TEMP_TAG=null;
            }
        }

    }

    private void uploadImageReq(String filePath){
        File uploadFile=new File(filePath);

        OkHttpUtils.post().url(BaseConfigValue.UPLOAD_IMAGE_URL)
                .addFile("files",uploadFile.getName(),uploadFile)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"err:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"success:"+response);
            }
        });
    }


}
