package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.Index05Result;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class Index5DetailBackAct extends BaseActivity {
    private String TAG="Index5DetailBackAct";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.leftBtn) View leftBtn;
    @BindView(R.id.fixname) TextView fixname;
    @BindView(R.id.fixtime) TextView fixtime;
    @BindView(R.id.fixUser) TextView fixUser;
    @BindView(R.id.remarkEdit) EditText remarkEdit;
    @BindView(R.id.uploadBtn) Button uploadBtn;

    private  Index05Result.ListBean fixErrItem;
    private PreferenceUtils preference;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_fix_err_back);
        ButterKnife.bind(this);
        title.setText("故障确认退回");
        preference=new PreferenceUtils(this);
        Intent intent = getIntent();
        fixErrItem = (Index05Result.ListBean) intent.getSerializableExtra("detail");

        fixname.setText(TextUtils.isEmpty(fixErrItem.getName())?"-":fixErrItem.getName());
        fixtime.setText(TextUtils.isEmpty(fixErrItem.getOverTime())?"-":fixErrItem.getOverTime());
        fixUser.setText(TextUtils.isEmpty(fixErrItem.getSendUserName())?"-":fixErrItem.getSendUserName());

    }

    @OnClick(R.id.uploadBtn)
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.uploadBtn:
                String remarkStr=remarkEdit.getText().toString().trim();
                if(TextUtils.isEmpty(remarkStr)){
                    showTips("退回原因不能为空，请填入后再试");
                }else{
                    submitBackData(fixErrItem.getId(), BaseApplication.instance.user_id, preference.getUserInfo().getRealname(),remarkStr);
                }
                break;
        }
    }

    private void submitBackData(String troubleId,String userid,String username,String Info){
        OkHttpUtils.post().url(BaseConfigValue.ERR_BACK_URL)
                .addParams("troubleid", troubleId)
                .addParams("userid", userid)
                .addParams("username", username)
                .addParams("Info", Info)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,e+"err:");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"退回数据:"+response);
                try {
                    JSONObject resultObj=new JSONObject(response);
                    if("1".equals(resultObj.getString("statecode"))){
                        showTips("退回成功");
                        setResult(RESULT_OK);
                        finish();
                    }else{
                        showTips("退回失败，请稍后再试");
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"err:"+e);
                    showTips("解析错误");
                }
            }
        });
    }
}
