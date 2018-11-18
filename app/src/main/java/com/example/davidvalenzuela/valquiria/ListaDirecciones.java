package com.example.davidvalenzuela.valquiria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.davidvalenzuela.valquiria.Adapter.DireccionAdapter;
import com.example.davidvalenzuela.valquiria.Clases.Direccion;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;
import com.example.davidvalenzuela.valquiria.DataBase.DireccionDataSource;

import java.util.ArrayList;
import java.util.List;

public class ListaDirecciones extends AppCompatActivity implements AdapterView.OnItemClickListener{

    DireccionDataSource datasource;

    ListView lvdirecciones;
    List<Direccion> direcciones;


    DireccionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direcciones);
        setTitle("Lista de Direcciones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvdirecciones = findViewById(R.id.lvDirecciones);
        direcciones = new ArrayList();
        datasource = new DireccionDataSource(this);
        lvdirecciones.setOnItemClickListener(this);
        actualizarLista();
    }
    public void actualizarLista(){

        datasource.openDB();
        direcciones = datasource.obtenerDirecciones();
        datasource.closeDB();

        adapter = new DireccionAdapter(this, R.layout.direccion_item,direcciones);
        lvdirecciones.setAdapter(adapter);
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
                        

                    }
                }).setNegativeButton("no", null);
        android.app.AlertDialog alertDialog = alertaGuardarContacto.create();
        alertDialog.show();
    }
}
