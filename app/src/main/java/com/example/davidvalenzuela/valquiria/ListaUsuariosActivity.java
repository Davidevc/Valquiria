package com.example.davidvalenzuela.valquiria;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidvalenzuela.valquiria.Adapter.UsuarioAdapter;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;
import com.example.davidvalenzuela.valquiria.DataBase.UsuariosDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener,AdapterView.OnItemClickListener {

    UsuariosDataSource datasource;

    ListView lvUsuarios;
    List<Usuario> usuarios;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        setTitle("Usuarios de la App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvUsuarios = findViewById(R.id.lvListaUsuarios);
        usuarios = new ArrayList();
        datasource = new UsuariosDataSource(this);
        lvUsuarios.setOnItemClickListener(this);

        request = Volley.newRequestQueue(this);

        cargarWebService();
    }

    private void cargarWebService() {
    progreso = new ProgressDialog(this);
    progreso.setMessage("Cargando...");
    progreso.show();

    String url = "http://192.168.64.2/dbRemota/wsJSONConsultarLista.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"Fallo la conexion"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
       Usuario usuario = null;
        JSONArray json= response.optJSONArray("usuario");
        try {
            for (int i=0;i<json.length();i++){
                usuario = new Usuario();

                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                usuario.setTelefono(jsonObject.optInt("telefono"));
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setApellido(jsonObject.optString("apellido"));

                usuarios.add(usuario);
            }

            progreso.hide();

            UsuarioAdapter adapter = new UsuarioAdapter(this, R.layout.usuario_item,usuarios);
            lvUsuarios.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder alertaGuardarContacto = new AlertDialog.Builder(this);
        alertaGuardarContacto.setMessage("¿ Desea Guardar este contacto ?")
                .setCancelable(false).setPositiveButton("si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        datasource.openDB();
                        Usuario usuario = usuarios.get(position);
                        usuario.setTelefono(usuario.getTelefono());
                        usuario.setNombre(usuario.getNombre());
                        usuario.setApellido(usuario.getApellido());

                        datasource.insertarUsuario(usuario);

                        datasource.closeDB();
                    }
                }).setNegativeButton("no", null);
        android.app.AlertDialog alertDialog = alertaGuardarContacto.create();
        alertDialog.show();
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
