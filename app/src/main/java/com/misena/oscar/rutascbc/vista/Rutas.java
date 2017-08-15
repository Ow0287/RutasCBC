package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.misena.oscar.rutascbc.R;

public class Rutas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
    }
    public  void irRuta(View v){
        Intent intent=new Intent (Rutas.this,Chat.class);
        startActivity(intent);
        finish();
    }
    public  void irChat(View v){
        Intent intent=new Intent (Rutas.this,Chat.class);
        startActivity(intent);
        finish();
    }
    public  void irGaleria(View v){
        Intent intent=new Intent (Rutas.this,Galeria.class);
        startActivity(intent);
        finish();
    }


    }

