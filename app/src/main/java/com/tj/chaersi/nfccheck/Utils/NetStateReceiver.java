package com.tj.chaersi.nfccheck.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by Lee on 2016/1/5.
 */
public class NetStateReceiver extends BroadcastReceiver {

    public interface onNETWORK_STATUS{public void onNet_STATUS_Listener(int state);}
    public static onNETWORK_STATUS netWorkListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            netWorkListener.onNet_STATUS_Listener(NetworkUtil.getNetworkType(context));
        }
    }
}
