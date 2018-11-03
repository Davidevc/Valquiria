package com.example.davidvalenzuela.valquiria;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

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

        lvUsuarios = findViewById(R.id.lvListaUsuarios);
        usuarios = new ArrayList();
        lvUsuarios.setOnItemClickListener((AdapterView.OnItemClickListener) this);

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
        Toast.makeText(this,"Registro fallido "+error.toString(),Toast.LENGTH_SHORT).show();
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
}
