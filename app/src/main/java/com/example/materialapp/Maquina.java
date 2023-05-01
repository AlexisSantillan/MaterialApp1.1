package com.example.materialapp;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

public class Maquina {
    private String id_maquina;
    private String nombre;
    private String descrip;
    private String cliente;
    private String meses;

    public Maquina(String id_maquina,
                   String nombre, String descrip,
                   String cliente, String meses) {
        this.id_maquina = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descrip = descrip;
        this.cliente = cliente;
        this.meses = meses;
    }

    public Maquina(Cursor cursor) {
        id_maquina = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.ID_MAQUINA));
        nombre = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.NOMBRE));
        descrip = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.DESCRIP));
        cliente = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.CLIENTE));

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(MaquinaContract.MaquinaEntry.ID_MAQUINA, id_maquina);
        values.put(MaquinaContract.MaquinaEntry.NOMBRE, nombre);
        values.put(MaquinaContract.MaquinaEntry.DESCRIP, descrip);
        values.put(MaquinaContract.MaquinaEntry.CLIENTE, cliente);

        return values;
    }

    public String getId_maquina() {
        return id_maquina;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getCliente() {
        return cliente;
    }
    public String getMeses() {
        return meses;
    }

}
