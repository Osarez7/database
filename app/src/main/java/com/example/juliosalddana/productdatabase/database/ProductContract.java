package com.example.juliosalddana.productdatabase.database;

import android.provider.BaseColumns;

public class ProductContract {

    //Creamos un contructor privado para que no se puedan crear instancias de esta clase.
    private ProductContract(){};

        /* Una clase interna que contiene la configuración de la table*/
        public static class ProductEntry implements BaseColumns {
            public static final String TABLE_NAME = "product";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_DESCRIPTION = "description";
        }
}
