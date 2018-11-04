package com.example.davidvalenzuela.valquiria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidvalenzuela.valquiria.Adapter.UsuarioAdapter;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Map;

public class LoginUsuario extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText telefono,contrasena;
    Button ingresar;
    TextView registrar;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        getSupportActionBar().hide();

        telefono = (EditText) findViewById(R.id.etTelefono);
        contrasena = (EditText) findViewById(R.id.etContrasena);

        request = Volley.newRequestQueue(this);

        ingresar = (Button) findViewById(R.id.btnIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

        registrar = (TextView) findViewById(R.id.txtRegistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    public void ingresar (){
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        String url="http://192.168.64.2/dbRemota/wsJSONConsultarUsuario.php?telefono="+telefono.getText().toString()
                +"&contrasena="+contrasena.getText().toString();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }

    public void irMain (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registrar (){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"error "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            String validador = response.getString("validador");
            if (validador.equals("true")){
                progreso.hide();
                Toast.makeText(this,"bienvenido" ,Toast.LENGTH_SHORT).show();
                irMain();
            }else {
                Toast.makeText(this,"error Telefono y/o contraseña" ,Toast.LENGTH_SHORT).show();
                progreso.hide();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
