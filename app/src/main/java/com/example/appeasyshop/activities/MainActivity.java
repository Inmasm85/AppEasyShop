package com.example.appeasyshop.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.appeasyshop.activities.adapters.CategoriasAdapter;
import com.example.appeasyshop.R;
import com.example.appeasyshop.core.db.CategoriasDAO;
import com.example.appeasyshop.core.entities.Categoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    private ListView categoriasListView;
    private CategoriasAdapter categoriasAdapter;
    private SearchView searchWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriasListView = findViewById(R.id.listViewCategorias);
        searchWords = findViewById(R.id.searchWords);

        CategoriasDAO categoriasDAO = CategoriasDAO.getInstance(this);
        List<Categoria> categorias = categoriasDAO.listarCategorias("");

        categoriasAdapter = new CategoriasAdapter(this, categorias);
        categoriasListView.setAdapter(categoriasAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración de eventos.
        searchWords.setOnQueryTextListener(this);
        categoriasListView.setOnItemClickListener(this);



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
            Intent intent = new Intent(this, ResumenPedidoActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        CategoriasDAO categoriasDAO = CategoriasDAO.getInstance(this);
        List<Categoria> categorias = categoriasDAO.listarCategorias(s);


        if (categorias.isEmpty()) {
            (Toast.makeText(this, getString(R.string.rs_categoria_no_encontrada), Toast.LENGTH_SHORT)).show();
        }
        else {
            categoriasAdapter.clear();
            categoriasAdapter.addAll(categorias);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        // Si el texto es vacío, se vuelven a listar todas las categorías
        if (s.isEmpty()) {
            CategoriasDAO categoriasDAO = CategoriasDAO.getInstance(this);
            List<Categoria> categorias = categoriasDAO.listarCategorias(s);
            categoriasAdapter.clear();
            categoriasAdapter.addAll(categorias);
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Categoria categoriaClicked = (Categoria) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(this, CategoriaActivity.class);
        intent.putExtra(CategoriaActivity.INTENT_EXTRA_CATEGORIA, categoriaClicked);
        startActivity(intent);
    }
}