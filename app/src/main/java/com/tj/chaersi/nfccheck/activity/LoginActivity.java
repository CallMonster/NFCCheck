package com.tj.chaersi.nfccheck.activity;

import android.content.Context;
import android.content.Intent;
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
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.Callback;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
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
                showProgressDialog("请稍后..");
                username=userEdit.getText().toString();
                password=passEdit.getText().toString();

                loginRequest();
                break;
        }
    }

    private void loginRequest(){
        OkHttpUtils.post().url(BaseConfigValue.LOGIN_URL)
                .addParams("username",username)
                .addParams("password",password)
                .build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"result:"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"result:"+response);
                if(response!=null){
                    LoginModel login=BaseApplication.gson.fromJson(response, LoginModel.class);

                    if("1".equals(login.getStatecode())){//正确的时候
                        login.getList().setPassword(password);
                        preference.saveUserInfo(login);
                        BaseApplication.instance.user_id=login.getList().getId();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if("2".equals(login.getStatecode())){//不正常的时候
                        showTips("用户名或密码错误");
                    }else{//异常的时候
                        showTips("服务器异常请稍后再试");
                    }
                }else{//访问出现问题
                    showTips("服务器异常请稍后再试");
                }
            }
        });
    }

}
