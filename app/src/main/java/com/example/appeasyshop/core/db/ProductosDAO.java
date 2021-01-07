package com.example.appeasyshop.core.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appeasyshop.core.entities.Categoria;
import com.example.appeasyshop.core.entities.Producto;
import com.example.appeasyshop.core.entities.ProductoPorCantidad;
import com.example.appeasyshop.core.entities.ProductoPorUnidad;

import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    private static ProductosDAO instance;
    private SqliteHelperEasyShop easyshopDB;

    private ProductosDAO(Context context) {
        easyshopDB = SqliteHelperEasyShop.getInstance(context);
    }

    public static ProductosDAO getInstance(Context context) {

        if (instance == null)
            instance = new ProductosDAO(context);

        return instance;
    }

    public List<Producto> listar(Categoria categoria) {
        return listar(categoria, "");
    }

    public List<Producto> listar(Categoria categoria, String parteNombre) {
        List<Producto> listadoCategorias = new ArrayList<>();
        SQLiteDatabase db = easyshopDB.getReadableDatabase();
        
        String [] projection = new String[] {
                ProductoEntry.COLUMN_NAME_ID,
                ProductoEntry.COLUMN_NAME_NOMBRE,
                ProductoEntry.COLUMN_NAME_PRECIOKG,
                ProductoEntry.COLUMN_NAME_PRECIOUD,
                ProductoEntry.COLUMN_NAME_DESCRIPCION
        };
        String selection = ProductoEntry.COLUMN_NAME_NOMBRE + " LIKE ? " +
                " AND " + ProductoEntry.COLUMN_NAME_CATEGORIA  + " = ? ";

        String [] selectionArgs = new String [] {
                parteNombre+"%",
                String.valueOf(categoria.getId())
        };
        
        Cursor cursor = db.query(
                ProductoEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                ProductoEntry.COLUMN_NAME_NOMBRE
        );

        int id;
        String nombre;
        String descripcion;
        double precioUnidad;
        double precioKg;

        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            nombre = cursor.getString(1);
            precioKg = cursor.getDouble(2);
            precioUnidad = cursor.getDouble(3);
            descripcion = cursor.getString(4);
            Producto producto;

            if (precioKg > 0) {
                producto = new ProductoPorCantidad(
                        id,
                        nombre,
                        descripcion,
                        precioKg
                );
            }
            else {
                producto = new ProductoPorUnidad(
                        id,
                        nombre,
                        descripcion,
                        precioUnidad
                );
            }


            listadoCategorias.add(producto);
        }

        return listadoCategorias;
    }

}
