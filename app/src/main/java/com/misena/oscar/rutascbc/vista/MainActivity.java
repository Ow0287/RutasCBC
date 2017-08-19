package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.controlador.ControladorParada;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Ruta> rutas = new ArrayList<>();
       ControladorRutas controladorRutas = new ControladorRutas();
        ControladorParada controladorParada=new ControladorParada();
        SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        boolean login=shared.getBoolean("login",false);

       if (controladorRutas.consultarTodasRutas().size() == 0) {

           //Rutas:
           rutas.add(new Ruta("Cuarta con 21 y 28"));
           rutas.get(0).addParada(new Paradas("I.C.B.F",rutas.get(0),10.473426, -73.248041));
           rutas.get(0).addParada(new Paradas("Parque De Los Varaos",rutas.get(0),10.467208, -73.243613));
           rutas.get(0).addParada(new Paradas("Donde Ponen Las Garzas",rutas.get(0),00000.000,0000.000));
           rutas.get(0).addParada(new Paradas("Cuarta Con 21",rutas.get(0),00000.000,0000.000));
           rutas.get(0).addParada(new Paradas("Colegio La Policia",rutas.get(0),00000.000,0000.000));
           rutas.get(0).addParada(new Paradas("Rosita Davila",rutas.get(0),00000.000,0000.000));


           rutas.add(new Ruta("Villa Miriam Por Fuera"));
           rutas.get(1).addParada(new Paradas("Primera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(1).addParada(new Paradas("Segunda Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(1).addParada(new Paradas("Tercera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(1).addParada(new Paradas("cuarta parada",rutas.get(0),00000.000,0000.000));
           rutas.get(1).addParada(new Paradas("Quinta Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(1).addParada(new Paradas("sexta Parada",rutas.get(0),00000.000,0000.000));
           rutas.add(new Ruta("Bello Horizonte"));
           rutas.get(2).addParada(new Paradas("Primera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(2).addParada(new Paradas("Segunda Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(2).addParada(new Paradas("Tercera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(2).addParada(new Paradas("cuarta parada",rutas.get(0),00000.000,0000.000));
           rutas.get(2).addParada(new Paradas("Quinta Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(2).addParada(new Paradas("sexta Parada",rutas.get(0),00000.000,0000.000));
           rutas.add(new Ruta("Mareiwa"));
           rutas.get(3).addParada(new Paradas("Primera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(3).addParada(new Paradas("Segunda Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(3).addParada(new Paradas("Tercera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(3).addParada(new Paradas("cuarta parada",rutas.get(0),00000.000,0000.000));
           rutas.get(3).addParada(new Paradas("Quinta Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(3).addParada(new Paradas("sexta Parada",rutas.get(0),00000.000,0000.000));
           rutas.add(new Ruta("La Paz"));
           rutas.get(4).addParada(new Paradas("Primera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(4).addParada(new Paradas("Segunda Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(4).addParada(new Paradas("Tercera Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(4).addParada(new Paradas("cuarta parada",rutas.get(0),00000.000,0000.000));
           rutas.get(4).addParada(new Paradas("Quinta Parada",rutas.get(0),00000.000,0000.000));
           rutas.get(4).addParada(new Paradas("sexta Parada",rutas.get(0),00000.000,0000.000));


           controladorRutas.llenarSitios(rutas);
           controladorParada.llenarParadas(rutas.get(0).listParadas);
           controladorParada.llenarParadas(rutas.get(1).listParadas);
           controladorParada.llenarParadas(rutas.get(2).listParadas);
           controladorParada.llenarParadas(rutas.get(3).listParadas);
           controladorParada.llenarParadas(rutas.get(4).listParadas);


           Log.e("las rutas estan", "vacias");
       }else {Log.e("si hay", "rutas");}

        if (login){

                Intent i =new Intent(MainActivity.this,MenuRutas.class);
                startActivity(i);
                finish();



        }else {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                /* Create an Intent that will start the MenuRutas-Activity. */
                    Intent mainIntent = new Intent(MainActivity.this,Login.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();
                }
            }, 3000);

        }
        }

       }


