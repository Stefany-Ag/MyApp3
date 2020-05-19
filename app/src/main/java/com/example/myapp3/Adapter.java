package com.example.myapp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapp3.modelo.Imagen;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Imagen> img;
    int ancho, alto;

    public Adapter(Context contexto, ArrayList<Imagen> img, int ancho, int alto) {
        this.contexto = contexto;
        this.img = img;
        this.ancho = ancho;
        this.alto = alto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return img.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.element_list,null);
        ImageView image = vista.findViewById(R.id.ivImagen);
        image.setLayoutParams(new ListView.LayoutParams(ancho/alto-20, ancho/alto-20));
        image.setPadding(15,15,15,15);
        Picasso.with(contexto)
                .load(contexto.getResources().getString(R.string.url_base)+img.get(position).getTumbnail_url())
                .into(image);
        TextView nombre = vista.findViewById(R.id.tvName);
        TextView proveedor = vista.findViewById(R.id.tvProv);
        TextView precio = vista.findViewById(R.id.tvPrice);
        TextView delivery = vista.findViewById(R.id.tvDeliv);
        nombre.setText(img.get(position).getName());
        proveedor.setText(img.get(position).getProvider());
        precio.setText(img.get(position).getPrice());
        delivery.setText(img.get(position).getDelivery());
        return vista;
    }
}
