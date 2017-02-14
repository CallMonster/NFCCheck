package com.tj.chaersi.nfccheck.activity;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
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

import com.google.gson.Gson;
import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.ImageUtils;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Data;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Judge;
import com.tj.chaersi.nfccheck.adapter.CheckDetailAdapter_Photo;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.impl.OnAdapterListener;
import com.tj.chaersi.nfccheck.vo.CheckPointDetailModel;
import com.tj.chaersi.nfccheck.vo.ImageUploadModel;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.nfccheck.vo.CheckPointDetailModel.AlldataBean;
import com.tj.chaersi.nfccheck.vo.CheckPointDetailModel.AlldataBean.SendInspectionDatasBean;
import com.tj.chaersi.nfccheck.vo.CheckPointDetailModel.AlldataBean.SendInspectionDatasBean.SendimagesBean;
import com.tj.chaersi.nfccheck.vo.PointDetailModel;
import com.tj.chaersi.nfccheck.widget.ListViewShowView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.builder.OkHttpRequestBuilder;
import com.tj.chaersi.okhttputils.builder.PostFormBuilder;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.opensrc.selectphoto.SystemAlbumPickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

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

    private String detailId;//"f5b737b2-ef22-4ead-b560-5ef56405fd06"
    private String nameStr,userIdStr,userNameStr,planTime,pointid,checktime;

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
        Intent intent=getIntent();
        detailId=intent.getStringExtra("routeId");
        nameStr=intent.getStringExtra("name");
        userIdStr=intent.getStringExtra("userId");
        userNameStr=intent.getStringExtra("userName");
        planTime=intent.getStringExtra("planTime");
        pointid=intent.getStringExtra("pointid");
        checktime=intent.getStringExtra("checktime");
        showProgressDialog("加载中..");
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
                break;
            case R.id.saveBtn:
                if(adapterPhoto==null&&adapterData==null&&adapterJudge==null){
                    showTips("已经完成了此巡检");
                    return;
                }

                ArrayList<HashMap<String,File>> photoUploadArr=new ArrayList<>();
                if(adapterPhoto!=null){
                    showProgressDialog("提交比较缓慢，请耐心等待...");
                    if(adapterPhoto.getCount()>0){
                        for(int i=0;i<adapterPhoto.getCount();i++){
                            HashMap<String,File> item=new HashMap<>();
                            if(TextUtils.isEmpty(adapterPhoto.getItemAArr().get(i).get("imgpath"))){
                                item.put("imgA",null);
                            }else{
                                item.put("imgA",new File(adapterPhoto.getItemAArr().get(i).get("imgpath")));
                            }
                            if(TextUtils.isEmpty(adapterPhoto.getItemBArr().get(i).get("imgpath"))) {
                                item.put("imgB",null);
                            }else{
                                item.put("imgB",new File(adapterPhoto.getItemBArr().get(i).get("imgpath")));

                            }
                            if(TextUtils.isEmpty(adapterPhoto.getItemCArr().get(i).get("imgpath"))) {
                                item.put("imgC",null);
                            }else{
                                item.put("imgC",new File(adapterPhoto.getItemCArr().get(i).get("imgpath")));
                            }
                            photoUploadArr.add(item);
                        }
                        uploadImageReq(photoUploadArr);
                    }else{
                        hideProgressDialog();
                        showTips("请选择图片后再试");
                    }
                }else{
                    CheckPointDetailModel detailModel=new CheckPointDetailModel();
                    AlldataBean alldataBean=new AlldataBean();
                    alldataBean.setRouteId(detailId);
                    alldataBean.setPointname(nameStr);
                    alldataBean.setPlanTime(planTime);
                    alldataBean.setUserId(userIdStr);
                    alldataBean.setPointid(pointid);
                    alldataBean.setUserName(userNameStr);
                    List<SendInspectionDatasBean> sendInspectionDatas=new ArrayList<SendInspectionDatasBean>();

                    if(adapterData!=null){
                        ArrayList<HashMap<String,String>> tempDataEditArr=adapterData.getEditArr();
                        for(int i=0;i<tempDataEditArr.size();i++){
                            SendInspectionDatasBean dataItem=new SendInspectionDatasBean();
                            dataItem.setId(dataArr.get(i).getId());
                            dataItem.setInfo(tempDataEditArr.get(i).get("editValue"));
                            dataItem.setIsAbnormal("");
                            dataItem.setName(dataArr.get(i).getName());
                            dataItem.setType("1");
                            List<SendimagesBean> dataimages=new ArrayList<SendimagesBean>();
                            dataItem.setSendimages(dataimages);
                            sendInspectionDatas.add(dataItem);
                        }
                    }

                    if(adapterJudge!=null){
                        ArrayList<HashMap<String,String>> tempJudgeArr=adapterJudge.getChooseArr();
                        for(int i=0;i<tempJudgeArr.size();i++){
                            SendInspectionDatasBean judgeItem=new SendInspectionDatasBean();
                            judgeItem.setId(judgeArr.get(i).getId());
                            judgeItem.setInfo(("remark"));
                            judgeItem.setIsAbnormal(tempJudgeArr.get(i).get("state"));
                            judgeItem.setName(judgeArr.get(i).getName());
                            judgeItem.setType("3");

                            List<SendimagesBean> judgeimages=new ArrayList<SendimagesBean>();
                            judgeItem.setSendimages(judgeimages);
                            sendInspectionDatas.add(judgeItem);
                        }
                    }

                    alldataBean.setSendInspectionDatas(sendInspectionDatas);
                    detailModel.setAlldata(alldataBean);
                    submitFormData(BaseApplication.gson.toJson(detailModel));
                }
                break;
        }
    }

    /**
     * 巡检点详情请求
     */
    private void pointDetailReq() {
        OkHttpUtils.post().url(BaseConfigValue.POINTDETAIL_URL)
                .addParams("id", detailId)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

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
                        checkUserName.setText(userNameStr);
                        checkTime.setText(checktime);
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

                adapterPhoto.setImagePath(TEMP_POSITION_CODE,TEMP_TAG,ImageUtils.getimage(imgPath));
                TEMP_POSITION_CODE=-1;
                TEMP_TAG=null;
            }
        }

    }

    /**
     * 上传图片
     * @param photoUploadArr
     */
    private void uploadImageReq(final ArrayList<HashMap<String,File>> photoUploadArr){
//        OkHttpUtils.post().url(BaseConfigValue.UPLOAD_IMAGE_URL)
//                .addFile("files",uploadFile.getName(),uploadFile)
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e(TAG,"err:"+e);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.i(TAG,"success:"+response);
//            }
//        });
        PostFormBuilder builder=OkHttpUtils.post().url(BaseConfigValue.UPLOAD_IMAGE_URL);
        for(HashMap<String,File> item:photoUploadArr){
            if(item.get("imgA")!=null){
                builder.addFile("files",item.get("imgA").getName(),item.get("imgA"));
            }
            if(item.get("imgB")!=null){
                builder.addFile("files",item.get("imgB").getName(),item.get("imgB"));
            }
            if(item.get("imgC")!=null){
                builder.addFile("files",item.get("imgC").getName(),item.get("imgC"));
            }
        }
        builder.build().connTimeOut(45000).readTimeOut(45000).execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"upload img err result:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"upload img succ result:"+response);
                ImageUploadModel imgModel=BaseApplication.gson.fromJson(response,ImageUploadModel.class);
                if("1".equals(imgModel.getStatecode())){
                    CheckPointDetailModel detailModel=new CheckPointDetailModel();
                    AlldataBean alldataBean=new AlldataBean();
                    alldataBean.setRouteId(detailId);
                    alldataBean.setPointname(nameStr);
                    alldataBean.setPlanTime(planTime);
                    alldataBean.setUserId(userIdStr);
                    alldataBean.setPointid(pointid);
                    alldataBean.setUserName(userNameStr);
                    List<SendInspectionDatasBean> sendInspectionDatas=new ArrayList<SendInspectionDatasBean>();

                    List<ImageUploadModel.ListBean> imgModelList=imgModel.getList();
                    for(int i=0,j=0;j<imgModelList.size()&&i<photoUploadArr.size();i++){
                        if(photoUploadArr.get(i).get("imgA")==null
                                &&photoUploadArr.get(i).get("imgB")==null
                                &&photoUploadArr.get(i).get("imgC")==null){
                            continue;
                        }else{
                            SendInspectionDatasBean inspectionItem=new SendInspectionDatasBean();
                            inspectionItem.setId(photoArr.get(i).getId());
                            inspectionItem.setInfo("");
                            inspectionItem.setIsAbnormal("");
                            inspectionItem.setName(photoArr.get(i).getName());
                            inspectionItem.setType("2");

                            List<SendimagesBean> sendimages=new ArrayList<SendimagesBean>();
                            if(photoUploadArr.get(i).get("imgA")!=null){
                                SendimagesBean imgBean=new SendimagesBean();
                                imgBean.setUrl(imgModelList.get(j).getUrl());
                                imgBean.setFileExt(imgModelList.get(j).getFileExt());
                                imgBean.setName(imgModelList.get(j).getName());
                                j++;
                                sendimages.add(imgBean);
                            }
                            if(photoUploadArr.get(i).get("imgB")!=null){
                                SendimagesBean imgBean=new SendimagesBean();
                                imgBean.setUrl(imgModelList.get(j).getUrl());
                                imgBean.setFileExt(imgModelList.get(j).getFileExt());
                                imgBean.setName(imgModelList.get(j).getName());
                                j++;
                                sendimages.add(imgBean);
                            }
                            if(photoUploadArr.get(i).get("imgC")!=null){
                                SendimagesBean imgBean=new SendimagesBean();
                                imgBean.setUrl(imgModelList.get(j).getUrl());
                                imgBean.setFileExt(imgModelList.get(j).getFileExt());
                                imgBean.setName(imgModelList.get(j).getName());
                                j++;
                                sendimages.add(imgBean);
                            }
                            inspectionItem.setSendimages(sendimages);
                            sendInspectionDatas.add(inspectionItem);
                        }
                    }

                    ArrayList<HashMap<String,String>> tempDataEditArr=adapterData.getEditArr();
                    for(int i=0;i<tempDataEditArr.size();i++){
                        SendInspectionDatasBean dataItem=new SendInspectionDatasBean();
                        dataItem.setId(dataArr.get(i).getId());
                        dataItem.setInfo(tempDataEditArr.get(i).get("editValue"));
                        dataItem.setIsAbnormal("");
                        dataItem.setName(dataArr.get(i).getName());
                        dataItem.setType("1");
                        List<SendimagesBean> dataimages=new ArrayList<SendimagesBean>();
                        dataItem.setSendimages(dataimages);
                        sendInspectionDatas.add(dataItem);
                    }

                    ArrayList<HashMap<String,String>> tempJudgeArr=adapterJudge.getChooseArr();
                    for(int i=0;i<tempJudgeArr.size();i++){
                        SendInspectionDatasBean judgeItem=new SendInspectionDatasBean();
                        judgeItem.setId(judgeArr.get(i).getId());
                        judgeItem.setInfo(("remark"));
                        judgeItem.setIsAbnormal(tempJudgeArr.get(i).get("state"));
                        judgeItem.setName(judgeArr.get(i).getName());
                        judgeItem.setType("3");

                        List<SendimagesBean> judgeimages=new ArrayList<SendimagesBean>();
                        judgeItem.setSendimages(judgeimages);
                        sendInspectionDatas.add(judgeItem);
                    }
                    alldataBean.setSendInspectionDatas(sendInspectionDatas);

                    detailModel.setAlldata(alldataBean);
//                    Log.i(TAG,BaseApplication.gson.toJson(detailModel));
                    hideProgressDialog();
                    submitFormData(BaseApplication.gson.toJson(detailModel.getAlldata()));
                }else{
                    showTips("图片上传有误。");
                }
            }
        });
    }

    /**
     * 保存巡检点详情
     */
    private void submitFormData(String saveData){
        showProgressDialog("数据保存中..");
        Log.i(TAG,"提交数据："+saveData);
//        OkHttpUtils.postString().url(BaseConfigValue.SAVE_CHECKDETAIL_URL)
//                .content(saveData)
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .build().execute(new StringCallback() {
        OkHttpUtils.post().url(BaseConfigValue.SAVE_CHECKDETAIL_URL)
                .addParams("alldata", saveData)
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"save data err result:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"save data:"+response);
                try {
                    JSONObject resultObj=new JSONObject(response);
                    if("1".equals(resultObj.getString("statecode"))){
                        showTips("保存数据成功");
                        setResult(RESULT_OK);
                        finish();
                    }else{
                        showTips("保存数据失败，请稍后再试");
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"err:"+e);
                    showTips("解析错误");
                }
            }
        });
    }


}
