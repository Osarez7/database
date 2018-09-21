package com.example.juliosalddana.productdatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Store.db";



    private static final String TABLE_CREATE =
            "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + "("+
                    ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ProductContract.ProductEntry.COLUMN_NAME + " TEXT, " +
                    ProductContract.ProductEntry.COLUMN_DESCRIPTION + " TEXT" +
                    ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME;

    public StoreDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(TABLE_CREATE);
    }
}
