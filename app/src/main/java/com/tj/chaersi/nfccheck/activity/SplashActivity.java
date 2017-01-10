package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;
import com.tj.chaersi.nfccheck.base.BaseApplication;
import com.tj.chaersi.nfccheck.base.BaseConfigValue;
import com.tj.chaersi.nfccheck.vo.LoginModel;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

public class SplashActivity extends AppCompatActivity {
    private String TAG="SplashActivity";
    private PreferenceUtils preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preference=new PreferenceUtils(this);
        if(TextUtils.isEmpty(preference.getUserInfo().getId())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    jumpLogin();
                }
            },3000);
        }else{
            loginRequest();
        }
    }

    private void loginRequest(){
        OkHttpUtils.post().url(BaseConfigValue.LOGIN_URL)
                .addParams("username",preference.getUserInfo().getUsername())
                .addParams("password",preference.getUserInfo().getPassword())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"result:"+e);
                jumpLogin();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG,"login result:"+response);
                if(response!=null){
                    LoginModel login=BaseApplication.gson.fromJson(response, LoginModel.class);

                    if("1".equals(login.getStatecode())){//正确的时候
                        login.getList().setPassword(preference.getUserInfo().getPassword());
                        preference.saveUserInfo(login);
                        BaseApplication.instance.user_id=login.getList().getId();
                        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{//异常的时候
                        jumpLogin();
                    }
                }else{//访问出现问题
                    jumpLogin();
                }
            }
        });
    }


    private void jumpLogin(){
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
