package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapp3.modelo.Imagen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/*
    Facultad de Ingeniería
    Computo móvil. Semestre 2020-1.
    Programado por: Cynthia Estefanía Arreola González.
*/
public class MainActivity extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONArray>{

    ListView lvLista;
    ProgressBar pbConnect;
    int ancho, alto;
    String url;
    public static final String ID = "ID";
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Imagen> img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLista = findViewById(R.id.lvLista);
        pbConnect = findViewById(R.id.pbConnect);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ancho = size.x;
        alto = size.y;
        img = new ArrayList<Imagen>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + R.string.url_img;
        request = new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConnect.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConnect.setVisibility(View.GONE);
        JSONObject obj;
        try{
            for(int i = 0; i < response.length(); i++){
                obj = response.getJSONObject(i);
                int id = obj.getInt(String.valueOf(R.string.id));
                String name = obj.getString(String.valueOf(R.string.name));
                String thumbnail_url = obj.getString(String.valueOf(R.string.thumbnail_url));
                String price = obj.getString(String.valueOf(R.string.price));
                String provider = obj.getString(String.valueOf(R.string.provider));
                String delivery = obj.getString(String.valueOf(R.string.delivery));
                Imagen imagen = new Imagen (id, name, thumbnail_url, price, provider, delivery);
                img.add(imagen);
            }
            Adapter adaptador = new Adapter(this,img,ancho,alto);
            lvLista.setAdapter(adaptador);
            lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra(ID,id);
                    startActivity(intent);
                }
            });
        }catch(JSONException e){
            Toast.makeText(this,R.string.error,Toast.LENGTH_SHORT).show();
        }
    }
}
