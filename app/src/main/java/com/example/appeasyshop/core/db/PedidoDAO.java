package com.example.appeasyshop.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.appeasyshop.core.entities.LineaPedido;
import com.example.appeasyshop.core.entities.Pedido;

public class PedidoDAO {

    private static PedidoDAO instance;
    private SqliteHelperEasyShop easyshopDB;

    private PedidoDAO(Context context) {
        easyshopDB = SqliteHelperEasyShop.getInstance(context);
    }

    public static PedidoDAO getInstance(Context context) {

        if (instance == null)
            instance = new PedidoDAO(context);

        return instance;
    }

    public void guardar(Pedido pedido) {
        SQLiteDatabase cursor = easyshopDB.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put(PedidoEntry.COLUMN_NAME_CLIENTE, pedido.getNombreCliente());
        data.put(PedidoEntry.COLUMN_NAME_TLFNO, pedido.getTelefono());

        long id = 0;
        try {
             id = cursor.insertOrThrow(PedidoEntry.TABLE_NAME, null, data);

            // Se insertan las lineas de pedido.
            for (LineaPedido linea : pedido.getLineas()) {
                ContentValues dataLinea = new ContentValues();
                dataLinea.put(LineaPedidoEntry.COLUMN_NAME_PEDIDO_ID, id);
                dataLinea.put(LineaPedidoEntry.COLUMN_NAME_PRODUCTO_ID, linea.getProducto().getId());
                dataLinea.put(LineaPedidoEntry.COLUMN_NAME_CANTIDAD, linea.getCantidad());
                cursor.insertOrThrow(LineaPedidoEntry.TABLE_NAME, null, dataLinea);
            }
        }
        catch (SQLiteException ex) {
            Log.e("DB_ERROR", ex.getMessage());
        }

    }

}
