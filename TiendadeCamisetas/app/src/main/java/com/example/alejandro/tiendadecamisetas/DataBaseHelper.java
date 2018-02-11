package com.example.alejandro.tiendadecamisetas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Alejandro on 11/02/2018.
 */

public class DataBaseHelper {
    private Context mCtx = null;
    private DataBaseHelperInternal mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME = "CAMISETAS";
    private static final int DATABASE_VERSION = 3;
    // tabla y campos
    private static final String DATABASE_TABLE_TODOLIST = "CAMISETAS";
    public static final String SL_ID = "_id";
    public static final String SL_NOMBRE = "nombre";
    public static final String SL_PRECIO = "precio";

    // SQL de creación de la tabla
    private static final String DATABASE_CREATE_TODOLIST =
            "create table "+ DATABASE_TABLE_TODOLIST +" ("+SL_ID+" integer primary key, "+SL_NOMBRE+" text not null, "+SL_PRECIO+" integer not null)";

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

    public Cursor getItems() {
        return mDb.query(DATABASE_TABLE_TODOLIST, new String[] {SL_ID, SL_NOMBRE, SL_PRECIO}, null, null, null, null, SL_ID);
    }

    public long insertItem(String nombre, int precio){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_NOMBRE, nombre);
        initialValues.put(SL_PRECIO, precio);
        return mDb.insert(DATABASE_TABLE_TODOLIST, null, initialValues);
    }

    public int delete(int mLastRowSelected) {
        return mDb.delete(DATABASE_TABLE_TODOLIST, SL_ID + "=?", new String[]{ Integer.toString(mLastRowSelected)});
    }

    public Cursor getItem(int itemId){
        return mDb.rawQuery(" select "+ SL_NOMBRE+","+ SL_PRECIO+"," + SL_ID + " from " + DATABASE_TABLE_TODOLIST  + " where " + SL_ID + "= ?",new String[]{Integer.toString(itemId)});
    }

    public int updateItem(int ident, String nombre, int precio) {
        ContentValues cv = new ContentValues();
        cv.put(SL_NOMBRE, nombre);
        cv.put(SL_PRECIO, precio);
        return mDb.update(DATABASE_TABLE_TODOLIST, cv, SL_ID + "=?", new String[]{Integer.toString(ident)});
    }
}