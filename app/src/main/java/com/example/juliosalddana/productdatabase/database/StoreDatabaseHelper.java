package com.example.juliosalddana.productdatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Store.db";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + ProductContract.TABLE_NAME + "("+
                    ProductContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ProductContract.COLUMN_NAME + " TEXT, " +
                    ProductContract.COLUMN_DESCRIPTION + " TEXT" +
                    ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductContract.TABLE_NAME;

    public StoreDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " +  ProductContract.TABLE_NAME +   " ADD COLUMN " + ProductContract.COLUMN_IMAGE_PATH + " TEXT");
        }
    }
}
