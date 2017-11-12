package com.example.android.bcmliteapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by keket on 18/10/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    //Member Variables
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BCMDb";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
            + SQLiteContract.SQLitePlan.TABLE_NAME;
    public static final String SQL_DELETE_ENTRIES = "TRUNCATE TABLE IF EXISTS "
            + SQLiteContract.SQLitePlan.TABLE_NAME;
    ;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SQLiteContract.SQLitePlan.TABLE_NAME + " (" +
                    SQLiteContract.SQLitePlan._ID + " INTEGER PRIMARY KEY," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_NAME + " TEXT," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_TYPE + " TEXT," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_INVOKED + " INTEGER DEFAULT 0," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_DEPARTMENT_NAME + " TEXT," +
                    SQLiteContract.SQLitePlan.COLUMN_NAME_DEPARTMENT_ID + " INTEGER" + ")";
    ;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //TODO: Update policy to retain data and only delete when the database changes
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
