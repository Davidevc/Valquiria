package com.example.davidvalenzuela.valquiria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
