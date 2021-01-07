package com.example.appeasyshop.core.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appeasyshop.core.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {

    private static CategoriasDAO instance;
    private SqliteHelperEasyShop easyshopDB;

    private CategoriasDAO(Context context) {
        easyshopDB = SqliteHelperEasyShop.getInstance(context);
    }

    public static CategoriasDAO getInstance(Context context) {

        if (instance == null)
            instance = new CategoriasDAO(context);

        return instance;
    }

    public List<Categoria> listarCategorias(String parteNombre) {
        List<Categoria> listadoCategorias = new ArrayList<>();
        SQLiteDatabase db = easyshopDB.getReadableDatabase();
        
        String [] projection = new String[] {
                CategoriaEntry.COLUMN_NAME_ID,
                CategoriaEntry.COLUMN_NAME_NOMBRE,
                CategoriaEntry.COLUMN_NAME_IMAGE_PATH
        };
        String selection = CategoriaEntry.COLUMN_NAME_NOMBRE + " LIKE ? ";
        String [] selectionArgs = new String [] {parteNombre+"%"};
        
        Cursor cursor = db.query(
                CategoriaEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                CategoriaEntry.COLUMN_NAME_ID
        );

        int id;
        String nombre;
        String pathToImage;

        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            nombre = cursor.getString(1);
            pathToImage = cursor.getString(2);
            listadoCategorias.add(new Categoria(id, nombre, pathToImage));
        }

        return listadoCategorias;
    }

}
