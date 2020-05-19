package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapp3.modelo.Imagen;
import com.example.myapp3.modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONArray>{

    TextView tvName2, tvDesc;
    ImageView ivProduct;
    ProgressBar pbConnect2;
    int ancho, alto;
    long id;
    String url;
    RequestQueue queue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pbConnect2 = findViewById(R.id.pbConnect2);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        id = bundle.getLong(MainActivity.ID);
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + "https://www.serverbpw.com/cm/2020-2/product_detail.php?id=" + id;
        request = new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConnect2.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConnect2.setVisibility(View.GONE);
        JSONObject obj;
        try{
            for(int i = 0; i < response.length(); i++){
                obj = response.getJSONObject(i);
                String name = obj.getString(String.valueOf(R.string.name));
                String imag_url = obj.getString(String.valueOf(R.string.imag_url));
                String desc = obj.getString(String.valueOf(R.string.desc));
                Producto product = new Producto (id, name, imag_url, desc);
            }
        }catch(JSONException e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}
