package com.example.appeasyshop.core.db;

import android.provider.BaseColumns;

public class LineaPedidoEntry implements BaseColumns {
    static final String TABLE_NAME = "lineasPedido";
    static final String COLUMN_NAME_PRODUCTO_ID = "producto_id";
    static final String COLUMN_NAME_PEDIDO_ID = "pedido_id";
    static final String COLUMN_NAME_CANTIDAD = "cantidad";
}
