package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.misena.oscar.rutascbc.R;

public class Login extends AppCompatActivity {
    EditText nombreE,contrasenaE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombreE=(EditText)findViewById(R.id.edt_usuario);
        contrasenaE=(EditText)findViewById(R.id.edt_contrasena_login);


    }
    public  void  login(View v){
        String nombre,contrasena;
        SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        nombre=shared.getString("nombre","");
        contrasena=shared.getString("contrasena","");

        if(nombre.equals(nombreE.getText().toString())&&(contrasena.equals(contrasenaE.getText().toString())))
        {
            Intent i =new Intent(Login.this, MenuRutas.class);
            startActivity(i);
            finish();

        }

    }
    public  void  registroLogin(View v){
        Intent i =new Intent(Login.this, RegistroLogin.class);
        startActivity(i);
        finish();

    }

}
