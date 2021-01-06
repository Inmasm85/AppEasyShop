package com.example.appeasyshop.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appeasyshop.core.entities.Categoria;

public class SqliteHelperEasyShop extends SQLiteOpenHelper {

    private static SqliteHelperEasyShop instance;
    private static final String DB_NAME = "EasyShop.db";
    private static final int DB_VERSION = 1;


    public SqliteHelperEasyShop(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    };


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                CategoriaEntry.COLUMN_NAME_ID + " ROWID); ");

        db.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                ProductoEntry.COLUMN_NAME_ID + " ROWID, " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + " TEXT, " +
                ProductoEntry.COLUMN_NAME_PRECIO + " REAL, " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + " INTEGER, " +
                " FOREIGN KEY(" + ProductoEntry.COLUMN_NAME_CATEGORIA + ") REFERENCES " +
                    CategoriaEntry.TABLE_NAME + "(" + CategoriaEntry.COLUMN_NAME_ID + ") " +
                "); ");

        // DML

        // Categorias
        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                    CategoriaEntry.COLUMN_NAME_ID + ", " +
                    CategoriaEntry.COLUMN_NAME_NOMBRE + ") " +
                " VALUES (1, 'BEBIDAS'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ") " +
                " VALUES (2, 'CHARCUTERIA'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ") " +
                " VALUES (3, 'PESCADERIA'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ") " +
                " VALUES (4, 'FRUTERIA'); ");

        // Productos

        db.execSQL("INSERT INTO " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_ID + ", " +
                ProductoEntry.COLUMN_NAME_NOMBRE + ", " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + ", " +
                ProductoEntry.COLUMN_NAME_PRECIO + ", " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + ") " +
                " VALUES (1, 'Cocacola', 'Una bebida con mucho azúcar', 1.35, 1); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // No hacer nada.
    }
}
