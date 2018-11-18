package com.example.davidvalenzuela.valquiria;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText telefono,nombre,apellido,contrasena,confirmarContrasena;
    Button registrar;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        telefono = (EditText) findViewById(R.id.etTelefono);
        nombre = (EditText) findViewById(R.id.etNombre);
        apellido = (EditText) findViewById(R.id.etApellido);
        contrasena = (EditText) findViewById(R.id.etContrasena);
        confirmarContrasena = (EditText) findViewById(R.id.etContrasenaConfirmar);

        registrar = (Button) findViewById(R.id.btnSiguiente);

        request = Volley.newRequestQueue(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contrasena.getText().toString().equals(confirmarContrasena.getText().toString())){
                cargarWebService();
                }else{
                     toastContrasena();
                }
            }
        });
    }

   private void cargarWebService() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        if (validador()){

        String url="http://192.168.64.2/dbRemota/wsJSONRegistro.php?telefono="+telefono.getText().toString()
                +"&nombre="+nombre.getText().toString()
                +"&apellido="+apellido.getText().toString()
                +"&contrasena="+contrasena.getText().toString();
        url = url.replace(" ","%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);}
    }

    private boolean validador() {
        boolean b=false;
        if (telefono.getText().toString().isEmpty()) {
            Toast.makeText(this, "Falta rellenar el Telefono ", Toast.LENGTH_SHORT).show();
        }else if (telefono.getText().length()<9) {
            Toast.makeText(this, "numero no valido\nintroduzca numero de 9 digitos\nejemplo: 9 1234 5678", Toast.LENGTH_LONG).show();}
            else if (nombre.getText().toString().isEmpty()){
                Toast.makeText(this, "Falta rellenar el nombre", Toast.LENGTH_SHORT).show();
            } else if (apellido.getText().toString().isEmpty()) {
                Toast.makeText(this, "Falta rellenar el apellido", Toast.LENGTH_SHORT).show();
            } else if (contrasena.getText().toString().isEmpty()) {
                Toast.makeText(this, "Falta rellenar la contraseña", Toast.LENGTH_SHORT).show();
            }else if (confirmarContrasena.getText().toString().isEmpty()) {
                Toast.makeText(this, "Falta rellenar confirmar contraseña", Toast.LENGTH_SHORT).show();
            }
                else{
                b=true;
            }
    progreso.hide();
    return b;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"Registro fallido "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            String validador = response.getString("validador");
            if (validador.equals("true")){
                progreso.hide();
                Toast.makeText(this,"Registro completado",Toast.LENGTH_LONG).show();
                telefono.setText("");
                nombre.setText("");
                apellido.setText("");
                contrasena.setText("");
                confirmarContrasena.setText("");
                finish();
            }else if (validador.equals("false")) {
                progreso.hide();
                Toast.makeText(this, "Usuario ya existente!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void toastContrasena(){
        Toast.makeText(this,"Error en el confirmar contraseña",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
