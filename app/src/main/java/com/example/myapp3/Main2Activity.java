package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONObject>{

    public static final String NAME = "name";
    public static final String IMAG = "imag_url";
    public static final String DESC = "desc";
    TextView tvName2, tvDesc;
    ImageView ivProduct;
    ProgressBar pbConnect2;
    long id;
    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvName2 = findViewById(R.id.tvName2);
        tvDesc = findViewById(R.id.tvDesc);
        ivProduct = findViewById(R.id.ivProduct);
        pbConnect2 = findViewById(R.id.pbConnect2);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        id = bundle.getLong(MainActivity.ID);
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + getResources().getString(R.string.url_prod) + id;
        request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pbConnect2.setVisibility(View.GONE);
                JSONObject obj;
                try{
                    obj = new JSONObject(response.toString());
                    String name = obj.getString(NAME);
                    String imag_url = obj.getString(IMAG);
                    String desc = obj.getString(DESC);
                    tvName2.setText(name);
                    tvDesc.setText(desc);
                    ivProduct.setPadding(15,15,15,15);
                    Picasso.with(Main2Activity.this)
                            .load(imag_url)
                            .into(ivProduct);
                }catch(JSONException e){
                    Toast.makeText(Main2Activity.this,R.string.error,Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(Main2Activity.this,"exito",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this,R.string.error,Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConnect2.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        pbConnect2.setVisibility(View.GONE);
        finish();
    }
}
