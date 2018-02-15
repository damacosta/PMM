package com.example.alejandro.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 14/02/2018.
 */

public class DataBaseHelper {
    private Context mCtx = null;
    private DataBaseHelperInternal mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME = "TODOLIST";
    private static final int DATABASE_VERSION = 3;
    // tabla y campos
    private static final String DATABASE_TABLE_TODOLIST = "TODOLIST";
    public static final String SL_ID = "_id";
    public static final String SL_NAME = "name";
    public static final String SL_URL = "url";

    private static final String DATABASE_CREATE_TODOLIST =
            "create table "+ DATABASE_TABLE_TODOLIST +" ("+SL_ID+" integer primary key, "+SL_NAME+" text not null, "+SL_URL+" text not null)";

    public DataBaseHelper(Context ctx) {
        this.mCtx = ctx;
    }

    private static class DataBaseHelperInternal extends SQLiteOpenHelper {
        public DataBaseHelperInternal(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);		}

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTables(db);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteTables(db);
            createTables(db);
        }
        private void createTables(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_TODOLIST);
        }

        private void deleteTables(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TODOLIST);
        }
    }

    public DataBaseHelper open()  {
        mDbHelper = new DataBaseHelperInternal(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //obtener todos los elementos
    public Cursor getItems() {
        return mDb.query(DATABASE_TABLE_TODOLIST, new String[] {SL_ID, SL_NAME, SL_URL}, null, null, null, null,SL_ID);
    }

    public long insertItem(String name, String url){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_NAME, name);
        initialValues.put(SL_URL, url);
        return mDb.insert(DATABASE_TABLE_TODOLIST, null, initialValues);
    }

    public int delete(int mLastRowSelected) {
        return mDb.delete(DATABASE_TABLE_TODOLIST, SL_ID + "=?", new String[]{ Integer.toString(mLastRowSelected)});
    }

    public Cursor getItem(int itemId){
        return mDb.rawQuery(" select "+ SL_NAME+","+ SL_URL+","+ SL_ID + " from " + DATABASE_TABLE_TODOLIST  + " where " + SL_ID + "= ?",new String[]{Integer.toString(itemId)});
    }

    public int updateItem(int ident, String name, String url) {
        ContentValues cv = new ContentValues();
        cv.put(SL_NAME, name);
        cv.put(SL_URL, url);
        return mDb.update(DATABASE_TABLE_TODOLIST, cv, SL_ID + "=?", new String[]{Integer.toString(ident)});
    }


}
