package com.example.appeasyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appeasyshop.core.entities.Producto;
import com.example.appeasyshop.core.entities.ProductoPorUnidad;

import java.util.List;

import androidx.annotation.NonNull;

public class ProductosAdapter extends ArrayAdapter<Producto> {

    public ProductosAdapter(@NonNull Context context, List<Producto> productoList) {
        super(context, 0, productoList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Producto producto = getItem(position);

        if (producto instanceof ProductoPorUnidad)
            textView.setText(String.format("%s (%s€) ", producto.getNombre(), producto.getPrecio()));
        else
            textView.setText(String.format("%s (%s Kg/€)", producto.getNombre(), producto.getPrecio()));
        // TODO: establecer imagen y precio

        return view;
    }

}
