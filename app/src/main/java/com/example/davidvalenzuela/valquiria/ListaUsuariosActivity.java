package com.example.davidvalenzuela.valquiria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class ListaUsuariosActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
