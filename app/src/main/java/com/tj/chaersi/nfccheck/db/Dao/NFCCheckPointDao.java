package com.tj.chaersi.nfccheck.db.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.tj.chaersi.nfccheck.db.DB.NFCCheckPointDB;
import com.tj.chaersi.nfccheck.db.DBInitDesign;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;

import java.util.List;

/**
 * Created by Chaersi on 17/1/11.
 */
public class NFCCheckPointDao {

    private NFCCheckPointDB dbHelper;
    public NFCCheckPointDao(Context context){
        dbHelper=new NFCCheckPointDB(context);
    }

    /**
     * insert all data
     * @param pointArr
     */
    public void insertAllBill(List<CheckPointModel.ListBean> pointArr){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            db.beginTransaction();
            for(CheckPointModel.ListBean item:pointArr){
                String sql ="INSERT INTO " + DBInitDesign.NFCCHECKPOINT_TABLE_NAME+
                " (point_id,point_createDate ,point_modifyDate ,point_companyID ,point_name ," +
                "point_distingguishType ,point_isLock ,point_latitudeAndLongitude ," +
                "point_labelNum ,point_num ,point_terriborialId ,point_version ,point_xqId ," +
                "point_type ,point_listOrder ,point_state ,point_plantime ,point_username) VALUES" +
                " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                db.execSQL(sql,new String[]{item.getId(), item.getCreateDate()+"",
                        item.getModifyDate()+"", item.getCompanyID(), item.getName(), item.getDistingguishType(),
                        item.isIsLock()?"1":"0", item.getLatitudeAndLongitude(), item.getLabelNum(), item.getNum(),
                        item.getTerriborialId(), item.getVersion(), item.getXqId(), item.getType(),
                        item.getListOrder(), item.getState(), item.getPlantime(), item.getUsername()});

            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 根据NFC进行详情的查询
     * @param nfcId
     * @return
     */
    public CheckPointModel.ListBean selectNFCItem(String nfcId){
        CheckPointModel.ListBean resultBean=new CheckPointModel.ListBean();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT _id,point_id,point_createDate ,point_modifyDate ,point_companyID ,point_name ," +
                "point_distingguishType ,point_isLock ,point_latitudeAndLongitude ," +
                "point_labelNum ,point_num ,point_terriborialId ,point_version ,point_xqId ," +
                "point_type ,point_listOrder ,point_state ,point_plantime ,point_username FROM " +
                DBInitDesign.NFCCHECKPOINT_TABLE_NAME+" WHERE point_labelNum=? ORDER BY _id;";

        Cursor cursor=db.rawQuery(sql,new String[]{nfcId.trim()});
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                resultBean.setId(cursor.getString(1));
                resultBean.setCreateDate(Long.parseLong(cursor.getString(2)));
                resultBean.setModifyDate(Long.parseLong(cursor.getString(3)));
                resultBean.setCompanyID(cursor.getString(4));
                resultBean.setName(cursor.getString(5));
                resultBean.setDistingguishType(cursor.getString(6));
                resultBean.setIsLock("1".equals(cursor.getString(7))?true:false);
                resultBean.setLatitudeAndLongitude(cursor.getString(8));
                resultBean.setLabelNum(cursor.getString(9));
                resultBean.setNum(cursor.getString(10));
                resultBean.setTerriborialId(cursor.getString(11));
                resultBean.setVersion(cursor.getString(12));
                resultBean.setXqId(cursor.getString(13));
                resultBean.setType(cursor.getString(14));
                resultBean.setListOrder(cursor.getString(15));
                resultBean.setState(cursor.getString(16));
                resultBean.setPlantime(cursor.getString(17));
                resultBean.setUsername(cursor.getString(18));
                break;
            }
            return resultBean;
        }else{
           return null;
        }

    }

    /**
     * 获取记录数目
     * @return
     */
    public Long getTableCount(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement("SELECT count(*) FROM checkpoint_table;");
        return statement.simpleQueryForLong();
    }

    /**
     * 清空之前的数据
     * @return
     */
    public void clearDb() {
        String sql = "DELETE FROM " + DBInitDesign.NFCCHECKPOINT_TABLE_NAME+ ";";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        revertSeq();
    }

    /**
     * 初始化DB
     */
    private void revertSeq() {
        String sql = "update sqlite_sequence set seq=0 where name='"+DBInitDesign.NFCCHECKPOINT_TABLE_NAME+ "'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

}
