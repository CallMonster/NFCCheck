package com.tj.chaersi.nfccheck.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.base.BaseActivity;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.Callback;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private String TAG="LoginActivity";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.userEdit) EditText userEdit;
    @BindView(R.id.passEdit) EditText passEdit;
    @BindView(R.id.forgetBtn) TextView forgetBtn;
    @BindView(R.id.loginBtn) Button loginBtn;

    PreferenceUtils preference;
    private String username,password;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        preference=new PreferenceUtils(this);
        passEdit.setInputType(EditorInfo.IME_ACTION_DONE);
        passEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    @Override
    @OnClick({R.id.forgetBtn, R.id.loginBtn})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.forgetBtn:
                break;
            case R.id.loginBtn:
                username=userEdit.getText().toString();
                password=passEdit.getText().toString();

                loginRequest();
                break;
        }
    }

    private void loginRequest(){
        HashMap<String,String> gsonMap=new HashMap<>();
        gsonMap.put("username","15100111111");
        gsonMap.put("password","000000");

        OkHttpUtils.postString().url(BaseConfigValue.LOGIN_URL)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(gsonMap))
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"result:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"result:"+response);

//                preference.saveUserInfo();

            }
        });
    }

}
