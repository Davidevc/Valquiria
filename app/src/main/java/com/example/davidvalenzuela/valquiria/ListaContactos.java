package com.example.davidvalenzuela.valquiria;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.davidvalenzuela.valquiria.Adapter.UsuarioAdapter;
import com.example.davidvalenzuela.valquiria.Adapter.UsuarioGuardadoAdapter;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;
import com.example.davidvalenzuela.valquiria.DataBase.UsuariosDataSource;

import java.util.ArrayList;
import java.util.List;

public class ListaContactos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    UsuariosDataSource datasource;

    ListView lvUsuarios;
    List<Usuario> usuarios;

    ProgressDialog progreso;

    UsuarioGuardadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        setTitle("Lista de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvUsuarios = findViewById(R.id.lvListaUsuario);
        usuarios = new ArrayList();
        datasource = new UsuariosDataSource(this);
        lvUsuarios.setOnItemClickListener(this);
        actualizarLista();
    }

    public void actualizarLista(){

        datasource.openDB();
        usuarios = datasource.obtenerUsuarios();
        datasource.closeDB();

        adapter = new UsuarioGuardadoAdapter(this, R.layout.usuario_listado_item,usuarios);
        lvUsuarios.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder alertaGuardarContacto = new AlertDialog.Builder(this);
        alertaGuardarContacto.setMessage("¿ Desea Eliminar este contacto ?")
                .setCancelable(false).setPositiveButton("si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        datasource.openDB();

                        Usuario usuario = usuarios.get(position);
                        int telefono = usuario.getTelefono();

                        datasource.eliminarUsuario(telefono);

                        datasource.closeDB();
                        actualizarLista();

                    }
                }).setNegativeButton("no", null);
        android.app.AlertDialog alertDialog = alertaGuardarContacto.create();
        alertDialog.show();
    }
}
