package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.adapter.AdapterRutas;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;
import java.util.List;

public class Rutas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
         List<Ruta> rutas=new ArrayList<>();

        final ListView lista=(ListView)findViewById(R.id.lis_rutas);
        ControladorRutas controladorRutas =new ControladorRutas();

       rutas= controladorRutas.consultarTodasRutas();
        AdapterRutas adapter =new AdapterRutas(rutas,this);
        lista.setAdapter(adapter);
        final List<Ruta> finalRutas = rutas;
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                irRuta(finalRutas.get(i));
            }
        });


    }
    public  void irRuta(Ruta ruta){
        Intent intent=new Intent (Rutas.this,Mapa.class);
        intent.putExtra("nombre",ruta.getNombre());
        startActivity(intent);
        finish();
    }


    }

