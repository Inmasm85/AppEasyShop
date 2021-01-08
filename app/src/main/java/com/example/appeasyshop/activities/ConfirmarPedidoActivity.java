package com.example.appeasyshop.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeasyshop.R;
import com.example.appeasyshop.activities.adapters.LineaPedidoResumenAdapter;
import com.example.appeasyshop.core.entities.Carrito;
import com.example.appeasyshop.core.entities.PedidoException;

public class ConfirmarPedidoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView totalTextView;
    private EditText telefonoEditText;
    private EditText nombreEditText;
    private Button btnVolver;
    private Button btnAceptar;
    private ListView lineasResumenListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        telefonoEditText = findViewById(R.id.editTextTelefono);
        nombreEditText = findViewById(R.id.editTextNombre);
        btnVolver = findViewById(R.id.botonVolver);
        btnAceptar = findViewById(R.id.buttonAceptar);
        totalTextView = findViewById(R.id.textTotalPedido);
        lineasResumenListView = findViewById(R.id.listViewResumenPedido);


        Carrito carrito = Carrito.getInstance();
        totalTextView.setText(String.format("%.2f", carrito.getTotal()));
        LineaPedidoResumenAdapter adapter = new LineaPedidoResumenAdapter(this, carrito.getProductos());
        lineasResumenListView.setAdapter(adapter);

        btnVolver.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);

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

        if (view.getId() == R.id.botonVolver) {
            finish();
        }else if (view.getId() == R.id.buttonAceptar) {
            String telefono = telefonoEditText.getText().toString();
            String nombre = nombreEditText.getText().toString();

            try {
                Carrito.getInstance().finalizar(this, nombre, telefono);

                (Toast.makeText(this, getString(R.string.pedido_confirmado), Toast.LENGTH_SHORT)).show();

                // Vuelta a la pantalla principal
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            catch(PedidoException ex) {
                mostrarMEnsajeErrorPedido(ex);
            }

        }

    }

    /**
     * Muestra el mensaje correcto para el error que se ha producido en el contacto.
     */

    private void mostrarMEnsajeErrorPedido(PedidoException ex) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        String mensaje = "";

        switch (ex.getErrCode()) {

            case PedidoException.ERR_NOMBRE_VACIO:
                mensaje = getString(R.string.err_pedido_nombre_vacio);
                break;

            case PedidoException.ERR_TELEFONO_INCORRECTO:
                mensaje = getString(R.string.err_pedido_telefono_incorrecto);
                break;

            case PedidoException.ERR_TELEFONO_VACIO:
                mensaje = getString(R.string.err_pedido_telefono_vacio);
                break;

            default:
                mensaje = getString(R.string.err_pedido_confirmacion);

        }

        dialogBuilder.setNegativeButton(R.string.btn_ok, null);
        dialogBuilder.setMessage(mensaje);

        Dialog dialog = dialogBuilder.create();
        dialog.show();
    }
}