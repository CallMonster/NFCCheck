package com.tj.chaersi.nfccheck.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.PreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    private PreferenceUtils preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preference=new PreferenceUtils(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(preference.getUserInfo().getId())){
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);

    }
}
