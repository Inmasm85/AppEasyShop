package com.example.appeasyshop.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appeasyshop.core.entities.Categoria;

import java.util.List;

public class CategoriasAdapter extends ArrayAdapter<Categoria> {

    public CategoriasAdapter(Context context, List<Categoria> listadoCategorias) {
        super(context, 0, listadoCategorias);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Categoria categoria = getItem(position);
        textView.setText(categoria.getNombre());

        return view;
    }
}
