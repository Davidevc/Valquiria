package com.example.davidvalenzuela.valquiria.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.davidvalenzuela.valquiria.Clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDataSource {

    public static final String TAG = "db";
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database; //para hacer las conexiones

    public UsuariosDataSource(Context context){
        dbhelper = new dbOpenHelper(context);
    }

    public void openDB(){
        database = dbhelper.getWritableDatabase();
        Log.i("db","openDB");
    }
    public void closeDB(){
        dbhelper.close();
        Log.i("db","closeDB");
    }

    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        Cursor cursor = database.rawQuery(query,null);//para retornar contacto de base de datos y se almacenan en un cursor

        Log.i(TAG,"Filar Retornadas: "+cursor.getCount());

        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                Usuario usuario = new Usuario();
                usuario.setTelefono(cursor.getInt(cursor.getColumnIndex("telefono")));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                usuario.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario insertarUsuario(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put("telefono",usuario.getTelefono());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());

        database.insert("usuario",null,valores);

        return usuario;
    }

    public boolean eliminarUsuario(int telefono){

        String where = "telefono="+telefono;

        int result = database.delete("usuario",where,null);
        Log.i("ContactoDataSourve", "se elimino el contacto con telefono : "+telefono);
        return(result == 1);

    }


}
