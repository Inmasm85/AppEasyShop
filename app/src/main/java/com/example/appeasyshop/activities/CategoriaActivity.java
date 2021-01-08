package com.example.appeasyshop.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.appeasyshop.activities.adapters.ProductosAdapter;
import com.example.appeasyshop.R;
import com.example.appeasyshop.core.db.ProductosDAO;
import com.example.appeasyshop.core.entities.Categoria;
import com.example.appeasyshop.core.entities.Producto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoriaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    public static final String INTENT_EXTRA_CATEGORIA = "categoria";

    private SearchView searchView;
    private ListView listViewProductos;
    private TextView categoriaText;
    private ProductosAdapter productosAdapter;
    private Categoria categoria;
    private ImageView categoriaImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchWords);
        listViewProductos = findViewById(R.id.listViewCategorias);
        categoriaText = findViewById(R.id.textPedido);
        categoriaImage = findViewById(R.id.imageLogo);

        Intent intent = getIntent();
        categoria = (Categoria) intent.getSerializableExtra(INTENT_EXTRA_CATEGORIA);

        // Rellenar valores
        categoriaText.setText(categoria.getNombre());

        ProductosDAO productosDAO = ProductosDAO.getInstance(this);
        List<Producto> productos =  productosDAO.listar(categoria);
        productosAdapter = new ProductosAdapter(this, productos);
        listViewProductos.setAdapter(productosAdapter);

        // Se establece la foto.
        try {
            InputStream in = getBaseContext().getAssets().open(categoria.getPathToImage());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            categoriaImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eventos
        searchView.setOnQueryTextListener(this);
        listViewProductos.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_pedido){
            Intent intent = new Intent(this, ResumenPedidoActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_categorias) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        ProductosDAO productosDAO = ProductosDAO.getInstance(this);
        List<Producto> productos = productosDAO.listar(categoria,s);


        if (productos.isEmpty()) {
            (Toast.makeText(this, getString(R.string.rs_productos_no_encontrados), Toast.LENGTH_SHORT)).show();
        }
        else {
            productosAdapter.clear();
            productosAdapter.addAll(productos);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        // Si el texto es vacío, se vuelven a listar todas las categorías
        if (s.isEmpty()) {
            ProductosDAO productosDAO = ProductosDAO.getInstance(this);
            List<Producto> productos = productosDAO.listar(categoria);
            productosAdapter.clear();
            productosAdapter.addAll(productos);
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Producto productoClicked = (Producto) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(this, com.example.appeasyshop.activities.Producto.class);
        intent.putExtra(com.example.appeasyshop.activities.Producto.INTENT_EXTRA_PRODUCTO, productoClicked);
        startActivity(intent);
    }
}