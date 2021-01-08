package com.example.appeasyshop.activities.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appeasyshop.R;
import com.example.appeasyshop.core.entities.Producto;
import com.example.appeasyshop.core.entities.ProductoPorUnidad;

import java.io.IOException;
import java.io.InputStream;
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
            view = inflater.inflate(R.layout.list_item_image_text, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.tvText);
        TextView subTextView = (TextView) view.findViewById(R.id.tvSubText);
        ImageView image = (ImageView) view.findViewById(R.id.ivImage);

        Producto producto = getItem(position);

        textView.setText(producto.getNombre());
        // Se establece la foto.
        try {
            InputStream in = getContext().getAssets().open(producto.getPathToImage());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (producto instanceof ProductoPorUnidad)
            subTextView.setText(String.format("%s € ",  producto.getPrecio()));
        else
            subTextView.setText(String.format("%s g/€",  producto.getPrecio()));

        return view;
    }

}
