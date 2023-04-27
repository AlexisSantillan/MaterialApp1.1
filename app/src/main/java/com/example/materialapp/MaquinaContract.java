package com.example.materialapp;

import android.provider.BaseColumns;

public class MaquinaContract {
    public static abstract class MaquinaEntry implements BaseColumns {
        public static final String TABLE_NAME ="Maquina";

        public static final String ID_MAQUINA = "id_maquina";
        public static final String NOMBRE = "name";
        public static final String DESCRIP = "descrip";
        public static final String CLIENTE = "cliente";
        public static final String MESES = "meses";

    }

}
