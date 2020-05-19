package com.example.myapp3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Constraints;

import com.example.myapp3.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context contexto;
    ArrayList<Producto> product;
    int ancho, alto;

    public ProductAdapter(Context contexto, ArrayList<Producto> product, int ancho, int alto) {
        this.contexto = contexto;
        this.product = product;
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int position) {
        return product.get(position);
    }

    @Override
    public long getItemId(int position) {
        return product.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(contexto);
        image.setLayoutParams(new Constraints.LayoutParams(ancho/alto-20, ancho/alto-20));
        image.setPadding(15,15,15,15);
        Picasso.with(contexto)
                .load(contexto.getResources().getString(R.string.url_base)+product.get(position).getImag_url())
                .into(image);
        TextView nombre = new TextView(contexto);
        return null;
    }
}
