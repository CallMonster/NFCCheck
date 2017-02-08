package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.ImageLoadUtils;
import com.tj.chaersi.nfccheck.Utils.ImageUtils;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.FixErrModel;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.builder.PostFormBuilder;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.opensrc.selectphoto.SystemAlbumPickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FixErrFinishActivity extends BaseActivity {
    private String TAG="FixErrFinishActivity";
    private int IMAGE_TAG_A=1;
    private int IMAGE_TAG_B=2;
    private int IMAGE_TAG_C=3;

    @BindView(R.id.title) TextView title;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.imgABtn) ImageView imgABtn;
    @BindView(R.id.imgBBtn) ImageView imgBBtn;
    @BindView(R.id.imgCBtn) ImageView imgCBtn;
    @BindView(R.id.uploadBtn) Button uploadBtn;

    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixUser) TextView fixUser;
    @BindView(R.id.remarkEdit) EditText remarkEdit;

    private FixErrModel.ListBean fixErrItem;
    private HashMap<String,String> imgMap;
    private PreferenceUtils preference;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err_finish);
        ButterKnife.bind(this);
        title.setText("维修完毕");
        imgMap=new HashMap<>();
        Intent intent = getIntent();
        fixErrItem = (FixErrModel.ListBean) intent.getSerializableExtra("detail");
        fixname.setText(TextUtils.isEmpty(fixErrItem.getName())?"-":fixErrItem.getName());
        fixtime.setText(TextUtils.isEmpty(fixErrItem.getOverTime())?"-":fixErrItem.getOverTime());
        fixUser.setText(TextUtils.isEmpty(fixErrItem.getSendUserName())?"-":fixErrItem.getSendUserName());

        preference=new PreferenceUtils(this);
    }

    @OnClick({R.id.leftBtn, R.id.imgABtn, R.id.imgBBtn, R.id.imgCBtn, R.id.uploadBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.imgABtn:
                Intent intent = new Intent(this, SystemAlbumPickerActivity.class);
                intent.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(intent, IMAGE_TAG_A);
                break;
            case R.id.imgBBtn:
                Intent intentB = new Intent(this, SystemAlbumPickerActivity.class);
                intentB.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(intentB, IMAGE_TAG_B);
                break;
            case R.id.imgCBtn:
                Intent intentC = new Intent(this, SystemAlbumPickerActivity.class);
                intentC.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(intentC, IMAGE_TAG_C);
                break;
            case R.id.uploadBtn:
                if(imgMap.isEmpty()){
                    showTips("请处理拍照后再试");
                }else{
                    showProgressDialog("提交中..");
                    submitFinishData(fixErrItem.getId(), BaseApplication.instance.user_id,
                            preference.getUserInfo().getRealname(),
                            remarkEdit.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgPath=data.getStringExtra(SystemAlbumPickerActivity.key_singlePath);
        if (resultCode == SystemAlbumPickerActivity.resultCode_SINGLE_PATH && requestCode == IMAGE_TAG_A) {
            imgABtn.setImageBitmap(ImageLoadUtils.pathToBitmap(ImageUtils.getimage(imgPath)));
            imgMap.put("A",imgPath);
        } else if (resultCode == RESULT_OK && requestCode == IMAGE_TAG_B) {
            imgBBtn.setImageBitmap(ImageLoadUtils.pathToBitmap(ImageUtils.getimage(imgPath)));
            imgMap.put("B",imgPath);
        } else if (resultCode == RESULT_OK && requestCode == IMAGE_TAG_C) {
            imgCBtn.setImageBitmap(ImageLoadUtils.pathToBitmap(ImageUtils.getimage(imgPath)));
            imgMap.put("C",imgPath);
        }
    }

    private void submitFinishData(String troubleId, String userid, String username, String Info) {
        PostFormBuilder builder=OkHttpUtils.post().url(BaseConfigValue.FIXERR_FINISH_URL);
        builder.addParams("troubleId", troubleId).addParams("userid", userid)
                .addParams("username", username).addParams("Info", Info);
        if(!TextUtils.isEmpty(imgMap.get("A"))){
            File imgAFile=new File(imgMap.get("A"));
            builder.addFile("files",imgAFile.getName(),imgAFile);
        }
        if(!TextUtils.isEmpty(imgMap.get("B"))){
            File imgBFile=new File(imgMap.get("B"));
            builder.addFile("files",imgBFile.getName(),imgBFile);
        }
        if(!TextUtils.isEmpty(imgMap.get("C"))){
            File imgCFile=new File(imgMap.get("C"));
            builder.addFile("files",imgCFile.getName(),imgCFile);
        }
        builder.build().connTimeOut(45000).readTimeOut(45000).execute(new StringCallback(){
            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"err:"+e);
                showTips("提交失败，请稍后再试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"succ:"+response);
                try {
                    JSONObject resultObj=new JSONObject(response);
                    if("1".equals(resultObj.getString("statecode"))){
                        showTips("提交成功");
                        setResult(RESULT_OK);
                        finish();
                    }else{
                        showTips("提交失败，请稍后再试");
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"err:"+e);
                    showTips("解析错误");
                }
            }
        });

    }

}
