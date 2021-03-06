package com.example.appeasyshop.core.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appeasyshop.core.entities.Categoria;
import com.example.appeasyshop.core.entities.LineaPedido;

public class SqliteHelperEasyShop extends SQLiteOpenHelper {

    private static SqliteHelperEasyShop instance;
    private static final String DB_NAME = "EasyShop.db";
    private static final int DB_VERSION = 1;



    private SqliteHelperEasyShop(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    };

    public static SqliteHelperEasyShop getInstance(Context context) {

        if (instance == null)
            instance = new SqliteHelperEasyShop(context);

        return  instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            // Cuando se abre la conexión, se activa el soporte para claves foráneas
            db.execSQL("PRAGMA foreign_keys = ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                CategoriaEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriaEntry.COLUMN_NAME_IMAGE_PATH + " TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                ProductoEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + " TEXT, " +
                ProductoEntry.COLUMN_NAME_PRECIOUD + " REAL, " +
                ProductoEntry.COLUMN_NAME_PRECIOKG + " REAL, " +
                ProductoEntry.COLUMN_NAME_PATH_TO_IMAGE + " TEXT, " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + " INTEGER, " +
                " FOREIGN KEY(" + ProductoEntry.COLUMN_NAME_CATEGORIA + ") REFERENCES " +
                    CategoriaEntry.TABLE_NAME + "(" + CategoriaEntry.COLUMN_NAME_ID + ") " +
                "); ");

        db.execSQL("CREATE TABLE " + PedidoEntry.TABLE_NAME + " (" +
                PedidoEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PedidoEntry.COLUMN_NAME_TLFNO + " VARCHAR NOT NULL, " +
                PedidoEntry.COLUMN_NAME_CLIENTE +  " VARCHAR NOT NULL); " );

        db.execSQL("CREATE TABLE " + LineaPedidoEntry.TABLE_NAME + " (" +
                LineaPedidoEntry.COLUMN_NAME_PEDIDO_ID + " INTEGER, " +
                LineaPedidoEntry.COLUMN_NAME_PRODUCTO_ID + " INTEGER, " +
                LineaPedidoEntry.COLUMN_NAME_CANTIDAD + " INTEGER, " +
                " PRIMARY KEY(" +  LineaPedidoEntry.COLUMN_NAME_PEDIDO_ID + ", " +
                        LineaPedidoEntry.COLUMN_NAME_PRODUCTO_ID + "), " +
                " FOREIGN KEY(" +  LineaPedidoEntry.COLUMN_NAME_PEDIDO_ID + ") REFERENCES " +
                        PedidoEntry.TABLE_NAME + "(" + PedidoEntry.COLUMN_NAME_ID + "), " +
                " FOREIGN KEY(" +  LineaPedidoEntry.COLUMN_NAME_PRODUCTO_ID + ")  REFERENCES " +
                        ProductoEntry.TABLE_NAME + "( " + ProductoEntry.COLUMN_NAME_ID + ") " + "); ");

                // DML

        // ------ Categorias ---------
        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                    CategoriaEntry.COLUMN_NAME_ID + ", " +
                    CategoriaEntry.COLUMN_NAME_NOMBRE + ", " +
                    CategoriaEntry.COLUMN_NAME_IMAGE_PATH + ") " +
                " VALUES (1, 'BEBIDAS', 'bebidas.jpg'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ", " +
                CategoriaEntry.COLUMN_NAME_IMAGE_PATH + ") " +
                " VALUES (2, 'CHARCUTERIA', 'charcuteria.jpg'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ", " +
                CategoriaEntry.COLUMN_NAME_IMAGE_PATH + ") " +
                " VALUES (3, 'PESCADERIA', 'pescaderia.png'); ");

        db.execSQL("INSERT INTO " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_ID + ", " +
                CategoriaEntry.COLUMN_NAME_NOMBRE + ", " +
                CategoriaEntry.COLUMN_NAME_IMAGE_PATH + ") " +
                " VALUES (4, 'FRUTERIA', 'fruteria.png'); ");


        // --------- Productos ----------
        db.execSQL("INSERT INTO " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_ID + ", " +
                ProductoEntry.COLUMN_NAME_NOMBRE + ", " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + ", " +
                ProductoEntry.COLUMN_NAME_PRECIOUD + ", " +
                ProductoEntry.COLUMN_NAME_PRECIOKG + ", " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + ", " +
                ProductoEntry.COLUMN_NAME_PATH_TO_IMAGE + ") " +
                " VALUES (1, 'Cocacola', 'Una bebida con mucho azúcar', 1.35, 0.00, 1, 'cola.jpg')," +
                " (2, 'Zumo de piña', 'Zumo natural recién exprimido', 1.50, 0.00, 1, 'zumopina.jpg')," +
                " (3, 'Aquarius', 'Bebida isotónica con sabor a naraja o limón', 1.55, 0.00, 1, 'aquarius.jpg'), " +
                " (4, 'Filetes de cerdo', 'Carne de cerdo fileteada de origen español', 0.00, 4.25, 2, 'filetesCerdo.jpeg')," +
                " (5, 'Pechuga de pollo', 'Carne de pollo criado en granjas de corral. Origen: España', 0.00, 3.40, 2, 'pechugapollo.jpeg')," +
                " (6, 'Lomo embuchado', 'Lomo de cerdo embuchado con especias', 0.00, 2.30, 2, 'lomo-embuchado.jpg')," +
                " (7, 'Calamares', 'Pescado fresco. Origen: Málaga', 0.00, 4.56, 3, 'calamares.jpeg')," +
                " (8, 'Salmón','Salmón noruego cortado al peso', 0.00, 5.34, 3, 'salmon.jpg')," +
                " (9, 'Tomate','Ricos tomates rojos', 0.00, 1.60, 4, 'tomate.png')," +
                " (10, 'Aguacate','Importados desde sudamérica', 0.00, 3.20, 4, 'aguacate.jpg')," +
                " (11, 'Lechuga','Producto procedente de la agricultura andaluza', 0.00, 0.8, 4, 'lechuga.jpg');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // No hacer nada.
    }

}
