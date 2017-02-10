package com.tj.chaersi.nfccheck.db.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tj.chaersi.nfccheck.db.DBInitDesign;

/**
 * Created by Chaersi on 17/2/10.
 */
public class ResidentDB extends SQLiteOpenHelper {

    public ResidentDB(Context context) {
        super(context, DBInitDesign.RESIDENT_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DBInitDesign.RESIDENT_TABLE_NAME
                +" (_id integer primary key autoincrement, resident_labelnum varchar(15)," +
                "resident_id varchar(40),resident_num varchar(40),resident_name varchar(40));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_save_sql="DROP table if exists "+DBInitDesign.RESIDENT_TABLE_NAME;
        db.execSQL(drop_save_sql);
        onCreate(db);
    }
}


