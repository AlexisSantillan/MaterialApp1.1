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
                + MaquinaContract.MaquinaEntry._ID + " integer primary key autoincrement,"
                + MaquinaContract.MaquinaEntry.ID_MAQUINA + " text not null,"
                + MaquinaContract.MaquinaEntry.NOMBRE + " text not null,"
                + MaquinaContract.MaquinaEntry.DESCRIP + " text not null,"
                + MaquinaContract.MaquinaEntry.CLIENTE + " text not null,"
                + MaquinaContract.MaquinaEntry.MESES + "text not null,"
                + "UNIQUE (" + MaquinaContract.MaquinaEntry.ID_MAQUINA + "))");

        mockData(db);
    }

    private void mockData(SQLiteDatabase db) {
        mockMaquina(db, new Maquina(" A-2", "Retroexcavadora",
                "año: 1999", "Alexis Cruz",
                "marzo-mayo"));
        mockMaquina(db, new Maquina("A-3", "Aplanadora",
                "año: 2003", "Daniel Samper",
                "junio-julio"));
        mockMaquina(db, new Maquina("A-4", "Abogado de derechos laborales",
                "300 200 3333", "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "lucia_aristizabal.jpg"));
        mockMaquina(db, new Maquina("A-5", "Abogado de familia",
                "300 200 4444", "Gran profesional con experiencia de 5 años en casos de familia.",
                "marina_acosta.jpg"));
        mockMaquina(db, new Maquina("A-6", "Abogado de administración pública",
                "300 200 5555", "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "olga_ortiz.jpg"));
        mockMaquina(db, new Maquina("A-7", "Abogado fiscalista",
                "300 200 6666", "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "pamela_briger.jpg"));


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

    public long saveLawyer(Maquina maquina) {
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

    public Cursor getMaquinaById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                null,
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return c;
    }

    public int deleteLawyer(String lawyerId) {
        return getWritableDatabase().delete(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateLawyer(Maquina maquina, String lawyerId) {
        return getWritableDatabase().update(
                MaquinaContract.MaquinaEntry.TABLE_NAME,
                maquina.toContentValues(),
                MaquinaContract.MaquinaEntry.ID_MAQUINA + " LIKE ?",
                new String[]{lawyerId}
        );
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(MaquinaContract.MaquinaEntry.ID_MAQUINA,"id_maquina");
        values.put(MaquinaContract.MaquinaEntry.NOMBRE, "nombre");
        values.put(MaquinaContract.MaquinaEntry.DESCRIP, "descripcion");
        values.put(MaquinaContract.MaquinaEntry.CLIENTE, "clienre");
        values.put(MaquinaContract.MaquinaEntry.MESES, "meses");


        return values;
    }
}
