package com.tj.chaersi.nfccheck.db.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tj.chaersi.nfccheck.db.DB.ResidentCheckDB;
import com.tj.chaersi.nfccheck.db.DBInitDesign;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;
import com.tj.chaersi.nfccheck.vo.Resident01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaersi on 17/2/9.
 */
public class ResidentCheckDao {

    private ResidentCheckDB dbHelper;
    public ResidentCheckDao(Context context){
        dbHelper=new ResidentCheckDB(context);
    }

    /**
     * insert all data
     * @param pointArr
     */
    public void insertAllBill(List<Resident01.ListBean> pointArr){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            db.beginTransaction();
            for(Resident01.ListBean item:pointArr){
                String sql ="INSERT INTO " + DBInitDesign.RESIDENTCHECK_TABLE_NAME+
                        " (resident_id,resident_createDate ,resident_modifyDate ,resident_companyID ,resident_name ," +
                        "resident_distingguishType ,resident_isLock ,resident_latitudeAndLongitude ," +
                        "resident_labelNum ,resident_num ,resident_terriborialId ,resident_version ,resident_xqId ," +
                        "resident_type ,resident_listOrder ,resident_state ,resident_plantime ,resident_username,resident_checktime)" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                db.execSQL(sql,new String[]{item.getId(), item.getCreateDate()+"",
                        item.getModifyDate()+"", item.getCompanyID(), item.getName(), item.getDistingguishType(),
                        item.isIsLock()?"1":"0", item.getLatitudeAndLongitude(), item.getLabelNum(), item.getNum(),
                        item.getTerriborialId(), item.getVersion(), item.getXqId(), item.getType(),
                        item.getListOrder(), item.getState(), item.getPlantime(), item.getUsername(),item.getChecktime()});

            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 清空之前的数据
     * @return
     */
    public void clearDb() {
        String sql = "DELETE FROM " + DBInitDesign.RESIDENTCHECK_TABLE_NAME+ ";";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        revertSeq();
    }

    /**
     * 初始化DB
     */
    private void revertSeq() {
        String sql = "update sqlite_sequence set seq=0 where name='"+DBInitDesign.RESIDENTCHECK_TABLE_NAME+ "'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

}
