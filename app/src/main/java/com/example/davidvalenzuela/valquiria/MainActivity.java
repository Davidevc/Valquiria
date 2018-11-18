package com.example.davidvalenzuela.valquiria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    //private TextView infoTextView;
    Button alerta;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alerta = (Button) findViewById(R.id.btn_alerta);
        request = Volley.newRequestQueue(this);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        //infoTextView = (TextView) findViewById(R.id.tvInfo);

       /* if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()){
                String value = getIntent().getExtras().getString(key);
                infoTextView.append("\n"+ key + ": "+value);
            }
        }*/

        alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            enviarAlerta();
            }
        });

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
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

            String url="http://192.168.64.2/dbRemota/FirebaseMensaje.php";
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
