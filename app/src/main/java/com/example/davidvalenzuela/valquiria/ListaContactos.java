package com.example.davidvalenzuela.valquiria;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.davidvalenzuela.valquiria.Adapter.UsuarioAdapter;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;
import com.example.davidvalenzuela.valquiria.DataBase.UsuariosDataSource;

import java.util.ArrayList;
import java.util.List;

public class ListaContactos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    UsuariosDataSource datasource;

    ListView lvUsuarios;
    List<Usuario> usuarios;

    ProgressDialog progreso;

    UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        setTitle("Usuarios de la App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvUsuarios = findViewById(R.id.lvListaUsuarios);
        usuarios = new ArrayList();
        datasource = new UsuariosDataSource(this);
        lvUsuarios.setOnItemClickListener(this);
        actualizarLista();
    }

    public void actualizarLista(){

        datasource.openDB();
        usuarios = datasource.obtenerUsuarios();
        datasource.closeDB();

        adapter = new UsuarioAdapter(this, R.layout.usuario_item,usuarios);
        lvUsuarios.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
