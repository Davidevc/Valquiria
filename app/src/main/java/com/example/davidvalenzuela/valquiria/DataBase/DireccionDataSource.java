package com.example.davidvalenzuela.valquiria.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.davidvalenzuela.valquiria.Clases.Direccion;
import com.example.davidvalenzuela.valquiria.Clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DireccionDataSource {

    public static final String TAG = "db";
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database; //para hacer las conexiones

    public DireccionDataSource(Context context){
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

    public List<Direccion> obtenerDirecciones(){
        List<Direccion> direcciones = new ArrayList<>();
        String query = "SELECT * FROM direccion";
        Cursor cursor = database.rawQuery(query,null);//para retornar contacto de base de datos y se almacenan en un cursor

        Log.i(TAG,"Filar Retornadas: "+cursor.getCount());

        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                Direccion direccion = new Direccion();
                direccion.setLatitud(cursor.getDouble(cursor.getColumnIndex("latitud")));
                direccion.setLongitud(cursor.getDouble(cursor.getColumnIndex("longitud")));
                direccion.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
                direccion.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                direcciones.add(direccion);
            }
        }
        return direcciones;
    }

    public Direccion insertarDireccion(Direccion direccion){

        ContentValues valores = new ContentValues();
        valores.put("latitud",direccion.getLatitud());
        valores.put("longitud",direccion.getLongitud());
        valores.put("direccion",direccion.getDireccion());
        valores.put("estado",direccion.getEstado());

        database.insert("direccion",null,valores);

        return direccion;
    }

    public boolean eliminarDireccion(int id){

        String where = "id="+id;

        int result = database.delete("direccion",where,null);
        Log.i("DireccionDataSourve", "se elimino direccion con id : "+id);
        return(result == 1);

    }


}
