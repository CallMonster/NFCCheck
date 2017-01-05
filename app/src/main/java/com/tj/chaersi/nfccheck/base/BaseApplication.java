package com.tj.chaersi.nfccheck.base;

import android.app.Application;

/**
 * Created by Chaersi on 17/1/4.
 */
public class BaseApplication extends Application {

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
