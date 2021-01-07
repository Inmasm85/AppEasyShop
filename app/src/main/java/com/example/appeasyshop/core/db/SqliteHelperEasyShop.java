package com.example.appeasyshop.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appeasyshop.core.entities.Categoria;

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
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CategoriaEntry.TABLE_NAME + " (" +
                CategoriaEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                CategoriaEntry.COLUMN_NAME_ID + " ROWID); ");

        db.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_NOMBRE + " VARCHAR NOT NULL, " +
                ProductoEntry.COLUMN_NAME_ID + " ROWID, " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + " TEXT, " +
                ProductoEntry.COLUMN_NAME_PRECIOUD + " REAL, " +
                ProductoEntry.COLUMN_NAME_PRECIOKG + " REAL, " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + " INTEGER, " +
                " FOREIGN KEY(" + ProductoEntry.COLUMN_NAME_CATEGORIA + ") REFERENCES " +
                    CategoriaEntry.TABLE_NAME + "(" + CategoriaEntry.COLUMN_NAME_ID + ") " +
                "); ");

        // DML

        // ------ Categorias ---------
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


        // --------- Productos ----------
        db.execSQL("INSERT INTO " + ProductoEntry.TABLE_NAME + " (" +
                ProductoEntry.COLUMN_NAME_ID + ", " +
                ProductoEntry.COLUMN_NAME_NOMBRE + ", " +
                ProductoEntry.COLUMN_NAME_DESCRIPCION + ", " +
                ProductoEntry.COLUMN_NAME_PRECIOUD + ", " +
                ProductoEntry.COLUMN_NAME_PRECIOKG + ", " +
                ProductoEntry.COLUMN_NAME_CATEGORIA + ") " +
                " VALUES (1, 'Cocacola', 'Una bebida con mucho azúcar', 1.35, 0.00, 1)," +
                " (2, 'Zumo de piña', 'Zumo natural recién exprimido', 1.50, 0.00, 1)," +
                " (3, 'Aquarius', 'Bebida isotónica con sabor a naraja o limón', 1.55, 0.00, 1), " +
                " (4, 'Filetes de cerdo', 'Carne de cerdo fileteada de origen español', 0.00, 4.25, 2)," +
                " (5, 'Pechuga de pollo', 'Carne de pollo criado en granjas de corral. Origen: España', 0.00, 3.40, 2)," +
                " (6, 'Lomo embuchado', 'Lomo de cerdo embuchado con especias', 0.00, 2.30, 2)," +
                " (7, 'Calamares', 'Pescado fresco. Origen: Málaga', 0.00, 4.56, 3)," +
                " (8, 'Salmón','Salmón noruego cortado al peso', 0.00, 5.34, 3)," +
                " (9, 'Tomate','Ricos tomates rojos', 0.00, 1.60, 4)," +
                " (10, 'Aguacate','Importados desde sudamérica', 0.00, 3.20, 4)," +
                " (11, 'Lechuga','Producto procedente de la agricultura andaluza', 0.00, 0.8, 4);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // No hacer nada.
    }
}
