package com.example.davidvalenzuela.valquiria;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.print.PageRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidvalenzuela.valquiria.Clases.Direccion;
import com.example.davidvalenzuela.valquiria.DataBase.DireccionDataSource;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    //private TextView infoTextView;
    Button alerta;
    DireccionDataSource datasource;
    Direccion direccion;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alerta = (Button) findViewById(R.id.btn_alerta);
        request = Volley.newRequestQueue(this);
        datasource = new DireccionDataSource(this);
        direccion = new Direccion();

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        Intent intent = getIntent();
        String message = intent.getStringExtra("mensaje");
        if(message != null){
            mostrarMensaje(message);
        }

        alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            enviarAlerta();
            }
        });

    }

    private void mostrarMensaje(String message) {
        AlertDialog.Builder alertaGuardarContacto = new AlertDialog.Builder(this);
        alertaGuardarContacto.setMessage(message)
                .setCancelable(false).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                }).setNegativeButton("Cancelar", null);
        android.app.AlertDialog alertDialog = alertaGuardarContacto.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.i_buscarContacto:
                Intent intent = new Intent(this, ListaUsuariosActivity.class);
                startActivity(intent);
                break;
            case R.id.i_AgregarDireccion:
                Intent intent1 = new Intent(this, RegistrarDireccionActivity.class);
                startActivity(intent1);
                break;
            case R.id.i_ListaContactos:
                Intent intent2 = new Intent(this, ListaContactos.class);
                startActivity(intent2);
                break;
            case R.id.i_ListaDireccion:
                Intent intent3 = new Intent(this, ListaDirecciones.class);
                startActivity(intent3);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void enviarAlerta() {
        datasource.openDB();
        direccion = datasource.direccionPredeterminada();
        datasource.closeDB();

        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

            String url="http://192.168.64.2/dbRemota/FirebaseMensaje.php?direccion="+direccion.getDireccion()
                    +"&longitud="+direccion.getLongitud()
                    +"&latitud="+direccion.getLatitud();
            url = url.replace(" ","%20");

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"Fallo en el envio, que te sigan pegando noma "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this,"Enviado",Toast.LENGTH_SHORT).show();
    }
}
