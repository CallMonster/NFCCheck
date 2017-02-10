package com.tj.chaersi.nfccheck.db.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tj.chaersi.nfccheck.db.DBInitDesign;

/**
 * Created by Chaersi on 17/2/9.
 */
public class ResidentCheckDB extends SQLiteOpenHelper {

    public ResidentCheckDB(Context context) {
        super(context, DBInitDesign.RESIDENTCHECK_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DBInitDesign.RESIDENTCHECK_TABLE_NAME
                +" (_id integer primary key autoincrement, resident_id varchar(40)," +
                "resident_createDate varchar(40),resident_modifyDate varchar(40)," +
                "resident_companyID varchar(40),resident_name varchar(40)," +
                "resident_distingguishType varchar(40),resident_isLock varchar(40)," +
                "resident_latitudeAndLongitude varchar(40),resident_labelNum varchar(40)," +
                "resident_num varchar(40),resident_terriborialId varchar(40)," +
                "resident_version varchar(40),resident_xqId varchar(40)," +
                "resident_type varchar(40),resident_listOrder varchar(40)," +
                "resident_state varchar(40),resident_plantime varchar(40)," +
                "resident_username varchar(40),resident_checktime varchar(40));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_save_sql="DROP table if exists "+DBInitDesign.RESIDENTCHECK_TABLE_NAME;
        db.execSQL(drop_save_sql);
        onCreate(db);
    }
}
