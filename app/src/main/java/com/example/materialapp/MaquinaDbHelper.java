package com.example.materialapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaquinaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "maquinas.db";

    public MaquinaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MatDatabase) {
        MatDatabase.execSQL("CREATE TABLE " + MaquinaContract.MaquinaEntry.TABLE_NAME + " ("
                + MaquinaContract.MaquinaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MaquinaContract.MaquinaEntry.ID_MAQUINA + " TEXT NOT NULL,"
                + MaquinaContract.MaquinaEntry.NOMBRE + " TEXT NOT NULL,"
                + MaquinaContract.MaquinaEntry.DESCRIP + " TEXT NOT NULL,"
                + MaquinaContract.MaquinaEntry.CLIENTE + " TEXT NOT NULL,"
                + "UNIQUE (" + MaquinaContract.MaquinaEntry.ID_MAQUINA + "))");


        //MatDatabase.execSQL("");

//        ContentValues values = new ContentValues();
//        values.put(MaquinaContract.MaquinaEntry.ID_MAQUINA, "a1");
//        values.put(MaquinaContract.MaquinaEntry.NOMBRE, " Excavadora");
//        values.put(MaquinaContract.MaquinaEntry.DESCRIP, "Modelo 2000");
//        values.put(MaquinaContract.MaquinaEntry.CLIENTE, "Carlos Santillan");
//
//        db.insert(MaquinaContract.MaquinaEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
