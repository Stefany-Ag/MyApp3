package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import com.example.myapp3.modelo.Producto;

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

    MediaPlayer mp;
    ListView lvLista;
    ProgressBar pbConnect;
    String url;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String THUMBNAIL = "thumbnail_url";
    public static final String PROVIDER = "provider";
    public static final String PRICE = "price";
    public static final String DELIVER = "delivery";
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Producto> img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(MainActivity.this, R.raw.watashi);
        mp.start();
        lvLista = findViewById(R.id.lvLista);
        pbConnect = findViewById(R.id.pbConnect);
        img = new ArrayList<Producto>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + getResources().getString(R.string.url_img);
        request = new JsonArrayRequest(Request.Method.POST, url,null,this,this);
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
                int id = obj.getInt(ID);
                String name = obj.getString(NAME);
                String thumbnail_url = obj.getString(THUMBNAIL);
                String price = obj.getString(PRICE);
                String provider = obj.getString(PROVIDER);
                String delivery = obj.getString(DELIVER);
                Producto producto = new Producto(id, name, thumbnail_url, price, provider, delivery);
                img.add(producto);
            }
            Adapter adaptador = new Adapter(this,img);
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

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mp.start();
    }
}
