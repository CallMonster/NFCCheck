package com.tj.chaersi.nfccheck.db.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tj.chaersi.nfccheck.db.DBInitDesign;

/**
 * Created by Chaersi on 17/1/11.
 */
public class NFCCheckPointDB extends SQLiteOpenHelper {

    public NFCCheckPointDB(Context context) {
        super(context, DBInitDesign.NFCCHECKPOINT_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DBInitDesign.NFCCHECKPOINT_TABLE_NAME
                +" (_id integer primary key autoincrement, point_id varchar(40)," +
                "point_createDate varchar(40),point_modifyDate varchar(40)," +
                "point_companyID varchar(40),point_name varchar(40)," +
                "point_distingguishType varchar(40),point_isLock varchar(40)," +
                "point_latitudeAndLongitude varchar(40),point_labelNum varchar(40)," +
                "point_num varchar(40),point_terriborialId varchar(40)," +
                "point_version varchar(40),point_xqId varchar(40)," +
                "point_type varchar(40),point_listOrder varchar(40)," +
                "point_state varchar(40),point_plantime varchar(40)," +
                "point_username varchar(40));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_save_sql="DROP table if exists "+DBInitDesign.NFCCHECKPOINT_TABLE_NAME;
        db.execSQL(drop_save_sql);
        onCreate(db);
    }
}
