package com.example.materialapp;

import android.content.ContentValues;

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

    public ContentValues toContentValues() {
    }
}
