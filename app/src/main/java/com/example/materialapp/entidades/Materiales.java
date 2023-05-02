package com.example.materialapp.entidades;

public class Materiales {

    private String nombre_mat;
    private String costo_mat;

    public Materiales(String nombre_mat, String costo_mat) {
        this.nombre_mat = nombre_mat;
        this.costo_mat = costo_mat;
    }

    public String getNombre_mat() {
        return nombre_mat;
    }

    public void setNombre_mat(String nombre_mat) {

        this.nombre_mat = nombre_mat;
    }

    public String getCosto_mat() {

        return costo_mat;
    }

    public void setCosto_mat(String costo_mat) {

        this.costo_mat = costo_mat;
    }
}
