package com.example.davidvalenzuela.valquiria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        getActionBar().hide();
    }
}
