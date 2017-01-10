package com.tj.chaersi.nfccheck.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tj.chaersi.nfccheck.vo.LoginModel;

import java.util.HashMap;

/**
 * Created by Chaersi on 17/1/9.
 */
public class PreferenceUtils {

    private final String SAVE_USER_KEY="USER_KEY";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public PreferenceUtils(Context context){
        preferences = context.getSharedPreferences(SAVE_USER_KEY, Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

    /**
     * 插入活动小红点提示
     * @return
     */
    public void saveUserInfo(LoginModel login){
        LoginModel.ListBean bean=login.getList();
        editor.putString("_id", TextUtils.isEmpty(bean.getId())?"":bean.getId());
        editor.putLong("_createDate",TextUtils.isEmpty(bean.getCreateDate()+"")?0l:bean.getCreateDate());
        editor.putLong("_modifyDate",TextUtils.isEmpty(bean.getModifyDate()+"")?0l:bean.getModifyDate());
        editor.putString("_username",TextUtils.isEmpty(bean.getUsername())?"":bean.getUsername());
        editor.putString("_realname",TextUtils.isEmpty(bean.getRealname())?"":bean.getRealname());
        editor.putString("_staffnum",TextUtils.isEmpty(bean.getStaffnum())?"":bean.getStaffnum());
        editor.putString("_station",TextUtils.isEmpty(bean.getStation())?"":bean.getStation());
        editor.putInt("_sortnum",TextUtils.isEmpty(bean.getSortnum()+"")?0:bean.getSortnum());
        editor.putString("_password",TextUtils.isEmpty(bean.getPassword())?"":bean.getPassword());
        editor.putBoolean("_status",bean.isStatus());
        editor.putString("_cellphone",TextUtils.isEmpty(bean.getCellphone())?"":bean.getCellphone());
        editor.putInt("_roleId",bean.getRoleId()==null?0:bean.getRoleId());
        editor.putString("_intre",TextUtils.isEmpty(bean.getIntro())?"":bean.getIntro());
        editor.putString("_orgID",TextUtils.isEmpty(bean.getOrgID())?"":bean.getOrgID());
        editor.commit();
    }

    public LoginModel.ListBean getUserInfo(){
        LoginModel.ListBean bean=new LoginModel.ListBean();
        bean.setId(preferences.getString("_id",bean.getId()));
        bean.setCreateDate(preferences.getLong("_createDate",0l));
        bean.setModifyDate(preferences.getLong("_modifyDate",0l));
        bean.setUsername(preferences.getString("_username",""));
        bean.setRealname(preferences.getString("_realname",""));
        bean.setStaffnum(preferences.getString("_staffnum",""));
        bean.setStation(preferences.getString("_station",""));
        bean.setSortnum(preferences.getInt("_sortnum",0));
        bean.setPassword(preferences.getString("_password",""));
        bean.setStatus(preferences.getBoolean("_status",false));
        bean.setCellphone(preferences.getString("_cellphone",""));
        bean.setRoleId(preferences.getInt("_roleId",0));
        bean.setIntro(preferences.getString("_intre",""));
        bean.setOrgID(preferences.getString("_orgID",""));
        return bean;
    }


}
