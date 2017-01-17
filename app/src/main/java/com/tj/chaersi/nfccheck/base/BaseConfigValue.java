package com.tj.chaersi.nfccheck.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Chaersi on 17/1/5.
 */
public class BaseConfigValue {
    public static final String BASE_URL = "http://115.28.210.100:8166/CloudPatrol/mobile";

    public static final String LOGIN_URL=BASE_URL+"/inspectiondata/login";
    public static final String CHECKPLAN_URL=BASE_URL+"/inspectionroutemobile/routelist";
    public static final String CHECKPOINT_URL=BASE_URL+"/inspectionrouteinfomobile/showrouteinfo";
    public static final String POINTDETAIL_URL=BASE_URL+"/inspectionrouteinfomobile/showproject";


}
