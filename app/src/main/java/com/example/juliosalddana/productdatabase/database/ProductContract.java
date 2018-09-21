package com.example.juliosalddana.productdatabase.database;

import android.provider.BaseColumns;

public class ProductContract implements BaseColumns  {

    //Creamos un contructor privado para que no se puedan crear instancias de esta clase.
    private ProductContract(){};

            public static final String TABLE_NAME = "product";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_IMAGE_PATH = "image_path";
}
