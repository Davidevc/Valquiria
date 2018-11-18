package com.example.davidvalenzuela.valquiria.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbOpenHelper extends SQLiteOpenHelper {

    public static final String AGENDA_CONTACTOS_DB = "agendaContactosUsuarios.db";
    public static final String tag = "OpenHeper";
    public static final int VERSION = 1;

    public String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (telefono INT PRIMARY KEY ," +
            "nombre TEXT," +
            "apellido TEXT)";



    public dbOpenHelper(Context context) {
        super(context, AGENDA_CONTACTOS_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL((CREATE_TABLE_USUARIO));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
