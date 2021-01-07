package com.example.appeasyshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeasyshop.core.entities.Carrito;
import com.example.appeasyshop.core.entities.ProductoPorCantidad;

import java.io.IOException;
import java.io.InputStream;

public class Producto extends AppCompatActivity implements View.OnClickListener{

    public static final String INTENT_EXTRA_PRODUCTO = "producto";

    private TextView nombreText;
    private TextView descripcionText;
    private TextView magnitudText;
    private EditText cantidad;
    private Button btnComprar;
    private ImageView imageView;

    private com.example.appeasyshop.core.entities.Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Asignación de valores.
        nombreText = findViewById(R.id.textPedido);
        descripcionText = findViewById(R.id.textDescripcionReceta);
        magnitudText = findViewById(R.id.textViewMagnitud);
        cantidad = findViewById(R.id.editTextCantidad);
        btnComprar = findViewById(R.id.buttonComprar);
        imageView = findViewById(R.id.imageView);

        // Rellenar valores.
        Intent intent = getIntent();
        producto = (com.example.appeasyshop.core.entities.Producto) intent.getSerializableExtra(INTENT_EXTRA_PRODUCTO);

        nombreText.setText(producto.getNombre());
        descripcionText.setText(producto.getDescripcion());

        if (producto instanceof ProductoPorCantidad)
            magnitudText.setText(getString(R.string.magnitud_cantidad));

        try {
            InputStream in = getBaseContext().getAssets().open(producto.getPathToImage());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configuración de eventos.
        btnComprar.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pedido) {
            return true;
        }

        if( id == R.id.action_categorias){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_recetas) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int valorCantidad = Integer.parseInt( cantidad.getText().toString() );

        Carrito.getInstance()
                .comprar(producto, valorCantidad);

        (Toast.makeText(this, getString(R.string.toast_producto_anniadido_carrito), Toast.LENGTH_SHORT)).show();

    }
}