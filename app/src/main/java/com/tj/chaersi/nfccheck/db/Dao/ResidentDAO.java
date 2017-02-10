package com.tj.chaersi.nfccheck.db.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tj.chaersi.nfccheck.db.DB.ResidentCheckDB;
import com.tj.chaersi.nfccheck.db.DB.ResidentDB;
import com.tj.chaersi.nfccheck.db.DBInitDesign;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;
import com.tj.chaersi.nfccheck.vo.Index01ResidentModel;
import com.tj.chaersi.nfccheck.vo.Resident01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaersi on 17/2/10.
 */
public class ResidentDAO {

    private ResidentDB dbHelper;
    public ResidentDAO(Context context){
        dbHelper=new ResidentDB(context);
    }

    /**
     * insert all data
     * @param pointArr
     */
    public void insertAllBill(List<Index01ResidentModel.ListBean> pointArr){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            db.beginTransaction();
            for(Index01ResidentModel.ListBean item:pointArr){
                String sql ="INSERT INTO " + DBInitDesign.RESIDENT_TABLE_NAME+
                        " (resident_labelnum,resident_id ,resident_num ,resident_name)" +
                        " VALUES (?,?,?,?);";
                db.execSQL(sql,new String[]{item.getLabelnum(),item.getId(),
                        item.getNum(),item.getName()});

            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 查询所有居民用户
     * @return
     */
    public List<Index01ResidentModel.ListBean> selectAllResident(){
        List<Index01ResidentModel.ListBean> resultArr=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT _id,resident_labelnum,resident_id,resident_num,resident_name FROM " +
                DBInitDesign.RESIDENT_TABLE_NAME+" ORDER BY _id;";

        Cursor cursor=db.rawQuery(sql,new String[]{});
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                Index01ResidentModel.ListBean item=new Index01ResidentModel.ListBean();
                item.setLabelnum(cursor.getString(1));
                item.setId(cursor.getString(2));
                item.setNum(cursor.getString(3));
                item.setName(cursor.getString(4));
                resultArr.add(item);
            }
            return resultArr;
        }else{
            return null;
        }
    }

    /**
     * 查询居民用户
     * @param str1
     * @return
     */
    public List<Index01ResidentModel.ListBean> selectResident(String str1){
        List<Index01ResidentModel.ListBean> resultArr=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT _id,resident_labelnum,resident_id,resident_num,resident_name FROM " +
                DBInitDesign.RESIDENT_TABLE_NAME+" WHERE resident_num=? OR resident_name=?" +
                " ORDER BY _id;";

        Cursor cursor=db.rawQuery(sql,new String[]{str1.trim()});
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                Index01ResidentModel.ListBean item=new Index01ResidentModel.ListBean();
                item.setLabelnum(cursor.getString(1));
                item.setId(cursor.getString(2));
                item.setNum(cursor.getString(3));
                item.setName(cursor.getString(4));
                resultArr.add(item);
            }
            return resultArr;
        }else{
            return null;
        }
    }

    /**
     * 清空之前的数据
     * @return
     */
    public void clearDb() {
        String sql = "DELETE FROM " + DBInitDesign.RESIDENT_TABLE_NAME+ ";";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        revertSeq();
    }

    /**
     * 初始化DB
     */
    private void revertSeq() {
        String sql = "update sqlite_sequence set seq=0 where name='"+DBInitDesign.RESIDENT_TABLE_NAME+ "'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

}
