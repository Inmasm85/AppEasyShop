package com.example.appeasyshop.core.db;

import android.provider.BaseColumns;

public class ProductoEntry implements BaseColumns {
    static final String TABLE_NAME = "productos";
    static final String COLUMN_NAME_NOMBRE = "nombre";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_PRECIOUD = "precioUD";
    static final String COLUMN_NAME_PRECIOKG = "precioKg";
    static final String COLUMN_NAME_DESCRIPCION = "descripcion";
    static final String COLUMN_NAME_CATEGORIA = "categoria";
    static final String COLUMN_NAME_PATH_TO_IMAGE = "image_path";
}
