package com.example.appeasyshop.activities.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeasyshop.R;
import com.example.appeasyshop.activities.ResumenPedidoActivity;
import com.example.appeasyshop.core.entities.LineaPedido;
import com.example.appeasyshop.core.entities.Producto;
import com.example.appeasyshop.core.entities.ProductoPorUnidad;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LineaProductoEditableAdapter extends BaseAdapter {
    private Context context;
    public static List<LineaPedido> lineaPedidos;
    private ResumenPedidoActivity pedidosActivity;

    public LineaProductoEditableAdapter(Context context, List<LineaPedido> lineaPedidos, ResumenPedidoActivity pedidosActivity) {
        this.context = context;
        this.lineaPedidos = lineaPedidos;
        this.pedidosActivity = pedidosActivity;
    }

    @Override
    public int getCount() {
        return lineaPedidos.size();
    }

    @Override
    public Object getItem(int i) {
        return lineaPedidos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lineaPedidos.get(i).getProducto().getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // ----
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_linea_pedido_edit, null, true);
            holder.editText = (EditText) convertView.findViewById(R.id.npCantidad);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tvText);
        TextView totalView = (TextView) convertView.findViewById(R.id.tvTotal);
        ImageView image = (ImageView) convertView.findViewById(R.id.ivImage);

        LineaPedido linea = lineaPedidos.get(position);
        Producto producto = linea.getProducto();


        textView.setText(producto.getNombre());
        totalView.setText(String.format("%.2f", linea.total()));
        // Se establece la foto.
        try {
            InputStream in = context.getAssets().open(producto.getPathToImage());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.editText.setText(String.valueOf(linea.getCantidad()));
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    lineaPedidos.get(position).setCantidad(Integer.parseInt(holder.editText.getText().toString()));
                    pedidosActivity.recalculateTotal();
                }
                catch (NumberFormatException ex) {
                    // Do nothing
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return convertView;

    }

    private class ViewHolder {
        protected EditText editText;
    }

}
