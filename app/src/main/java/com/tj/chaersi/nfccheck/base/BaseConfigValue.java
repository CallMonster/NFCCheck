package com.tj.chaersi.nfccheck.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Chaersi on 17/1/5.
 */
public class BaseConfigValue {
    public static final String BASE_URL = "http://115.28.210.100:8166/CloudPatrol/mobile";

    public static final String LOGIN_URL=BASE_URL+"/inspectiondata/login";//登录
    public static final String CHECKPLAN_URL=BASE_URL+"/inspectionroutemobile/routelist";//巡检计划
    public static final String CHECKPOINT_URL=BASE_URL+"/inspectionrouteinfomobile/showrouteinfo";//路线站点详情
    public static final String POINTDETAIL_URL=BASE_URL+"/inspectionrouteinfomobile/showproject";//站点巡检项目详情
    public static final String FIXERR_URL=BASE_URL+"/mtrouble/wxlist";//维修待办

    public static final String CHECKRESIDENT_URL=BASE_URL+"";//定位小区
    public static final String SAVE_CHECKDETAIL_URL=BASE_URL+"/inspectiondata/filesup";//保存巡检点详情


    public static final String UPLOAD_IMAGE_URL=BASE_URL+"/inspectiondata/uploadFile";//上传图片

}
