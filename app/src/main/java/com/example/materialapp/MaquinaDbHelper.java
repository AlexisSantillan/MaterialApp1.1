package com.example.materialapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaquinaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "maquinas.db";

    public MaquinaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MaquinaContract.MaquinaEntry.TABLE_NAME + " ("
                + MaquinaContract.MaquinaEntry.ID_MAQUINA + " integer primary key autoincrement,"
                + MaquinaContract.MaquinaEntry.NOMBRE + " text not null,"
                + MaquinaContract.MaquinaEntry.DESCRIP + " text not null,"
                + MaquinaContract.MaquinaEntry.CLIENTE + " text not null,"
                + MaquinaContract.MaquinaEntry.MESES + "text not null,"
                + "UNIQUE (" + MaquinaContract.MaquinaEntry.ID_MAQUINA + "))");

        mockData(db);
    }

    private void mockData(SQLiteDatabase db) {
    }

    public long mockMaquina(SQLiteDatabase db, Maquina maquina) {
        return db.insert(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                null,
                maquina.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveMaquina(Maquina maquina) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                null,
                maquina.toContentValues());

    }

    public Cursor getAllMaquinas() {
        return getReadableDatabase()
                .query(
                        MaquinaContract.MaquinaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getMaquinaById(String maquinaId) {
        Cursor c = getReadableDatabase().query(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                null,
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{maquinaId},
                null,
                null,
                null);
        return c;
    }

    public int deleteMaquina(String maquinaId) {
        return getWritableDatabase().delete(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{maquinaId});
    }

    public int updateMaquina(Maquina maquina, String maquinaId) {
        return getWritableDatabase().update(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                maquina.toContentValues(),
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{maquinaId}
        );
    }

}
