package com.example.appeasyshop.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.appeasyshop.R;
import com.example.appeasyshop.activities.adapters.LineaProductoEditableAdapter;
import com.example.appeasyshop.core.entities.Carrito;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResumenPedidoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSeguirComprando;
    private Button btnFinalizarPedido;
    private ListView lvListaPedido;
    private LineaProductoEditableAdapter lineasProductoAdapter;
    private TextView textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvListaPedido = findViewById(R.id.listViewCategorias);
        btnSeguirComprando = findViewById(R.id.buttonSeguirComprando);
        btnFinalizarPedido = findViewById(R.id.btnFinalizarPedido);
        textTotal = findViewById(R.id.textTotalPedido);

        Carrito carrito = Carrito.getInstance();

        lineasProductoAdapter= new LineaProductoEditableAdapter(getBaseContext(), carrito.getProductos(), this);
        lvListaPedido.setAdapter(lineasProductoAdapter);
        lvListaPedido.setEmptyView(findViewById(R.id.emptyList));
        lvListaPedido.setItemsCanFocus(true);

        textTotal.setText(String.format("%s %.2f €", textTotal.getText().toString(), carrito.getTotal()));


        btnSeguirComprando.setOnClickListener(this);
        btnFinalizarPedido.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void recalculateTotal() {
        textTotal.setText(String.format("%s %.2f €", getString(R.string.textTotal), Carrito.getInstance().getTotal()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_categorias) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.buttonSeguirComprando) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btnFinalizarPedido) {

            if (Carrito.getInstance().getTotal() <= 0) {
                mostrarMensajeErrorContacto(getString(R.string.rs_problema_en_pedido));
                //(Toast.makeText(this, getString(R.string.rs_problema_en_pedido), Toast.LENGTH_LONG)).show();
            }
            else {
                Intent intent = new Intent(this, ConfirmarPedidoActivity.class);
                startActivity(intent);
            }
        }

    }



    /**
     * Muestra el mensaje correcto para el error que se ha producido en el contacto.
     */

    private void mostrarMensajeErrorContacto(String mensaje) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);;

        dialogBuilder.setNegativeButton(R.string.btn_ok, null);
        dialogBuilder.setMessage(mensaje);

        Dialog dialog = dialogBuilder.create();
        dialog.show();
    }
}