package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.controlador.ControladorAdministrador;
import com.misena.oscar.rutascbc.controlador.ControladorParada;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.ModelAdmin;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ModelAdmin administrador;
    ControladorAdministrador controladorAdministrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Ruta> rutas = new ArrayList<>();
       ControladorRutas controladorRutas = new ControladorRutas();
        ControladorParada controladorParada=new ControladorParada();
        SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);

        controladorAdministrador =new ControladorAdministrador();
        if (controladorAdministrador.admin()){
            administrador=new ModelAdmin("AdminSena","123456");
            administrador.save();
        }
        boolean login=shared.getBoolean("login",false);

       if (controladorRutas.consultarTodasRutas().size() == 0) {

           //Rutas:
           rutas.add(new Ruta("Cuarta con 21 y 28"));
           rutas.get(0).addParada(new Paradas("I.C.B.F",rutas.get(0),10.473426,-73.248041));
           rutas.get(0).addParada(new Paradas("Parque De Los Varaos",rutas.get(0),10.467208,-73.243613));
           rutas.get(0).addParada(new Paradas("Donde Ponen Las Garzas",rutas.get(0),10.464507,-73.239567));
           rutas.get(0).addParada(new Paradas("Cuarta Con 21",rutas.get(0),10.465220,-73.232883));
           rutas.get(0).addParada(new Paradas("Cuarta Con 28",rutas.get(0),10.462660,-73.230510));
           rutas.get(0).addParada(new Paradas(" Tienda 28  ",rutas.get(0),10.460770,-73.233166));
           rutas.get(0).addParada(new Paradas("Colegio La Policia",rutas.get(0),10.458749,-73.236421));
           rutas.get(0).addParada(new Paradas("Rosita Davila",rutas.get(0),10.458340,-73.240511));
           rutas.get(0).addParada(new Paradas("28 con septima",rutas.get(0),10.457710,-73.243169));
           rutas.get(0).addParada(new Paradas("Estacion de Servicio ECOS",rutas.get(0),10.452033,-73.242944));
           rutas.get(0).addParada(new Paradas("C.B.C",rutas.get(0),10.406489,-73.236519));

           rutas.add(new Ruta("Los Poporos-Villa Miriam Por Fuera"));
           rutas.get(1).addParada(new Paradas("Los Poporos",rutas.get(1),10.474523,-73.262911));
           rutas.get(1).addParada(new Paradas("Casa del Abuelo",rutas.get(1),10.471158,-73.260592));
           rutas.get(1).addParada(new Paradas("COM",rutas.get(1),10.470620, -73.257867));
           rutas.get(1).addParada(new Paradas("EXITO las Flores",rutas.get(1),10.469663,-73.257996));
           rutas.get(1).addParada(new Paradas("Drogueria La Economia-La Popa",rutas.get(1),10.464879,-73.266098));
           rutas.get(1).addParada(new Paradas("Glorieta  La Chichamaya",rutas.get(1),10.462022,-73.274057));
           rutas.get(1).addParada(new Paradas("Drogueria Manantial",rutas.get(1),10.454401,-73.268634));
           rutas.get(1).addParada(new Paradas("Parque Casimiro",rutas.get(1),10.451097,-73.265503));
           rutas.get(1).addParada(new Paradas("Ovelisco",rutas.get(1),10.451097,-73.265503));
           rutas.get(1).addParada(new Paradas("44 esquina del 25",rutas.get(1),10.445768,-73.245837));
           rutas.get(1).addParada(new Paradas("Uparsistem",rutas.get(1),10.438023,-73.245086));
           rutas.get(1).addParada(new Paradas("C.B.C",rutas.get(1),10.406489,-73.236519));


           rutas.add(new Ruta("San Fernando"));
           rutas.get(2).addParada(new Paradas("Ceiba-Simon Bolivar",rutas.get(2),10.465086,-73.252777));
           rutas.get(2).addParada(new Paradas("Calle 37-Drogueria San Martin",rutas.get(2),10.452066,-73.245449));
           rutas.get(2).addParada(new Paradas("Estacion de Servicio ECOS",rutas.get(2),10.452033,-73.242944));
           rutas.get(2).addParada(new Paradas("Servipan",rutas.get(2),10.450625, -73.242529));
           rutas.get(2).addParada(new Paradas("Parque San Fernando",rutas.get(2),10.445575,-73.237262));
           rutas.get(2).addParada(new Paradas("Terminal",rutas.get(2),10.444971,-73.243141));
           rutas.get(2).addParada(new Paradas("C.B.C",rutas.get(2),10.406489,-73.236519));



           rutas.add(new Ruta("RUTA COOMEVA – Kra 4TA - MAYALES"));
           rutas.get(3).addParada(new Paradas("Tienda La Canoa",rutas.get(3),10.480833,-73.244430));
           rutas.get(3).addParada(new Paradas("4ta con 18 esquina",rutas.get(3),10.475033,-73.240195));
           rutas.get(3).addParada(new Paradas("4ta-Piedra Blanca",rutas.get(3),10.471006,-73.237277));
           rutas.get(3).addParada(new Paradas("Cuarta Con 21",rutas.get(3),10.465220,-73.232883));
           rutas.get(3).addParada(new Paradas("Colegio Fransisco Molina",rutas.get(3),10.460477,-73.229034));
           rutas.get(3).addParada(new Paradas("Entrada los Cocos",rutas.get(3),10.457772,-73.233034));
           rutas.get(3).addParada(new Paradas("Parque Algarrobillos",rutas.get(3),10.457348,-73.234672));
           rutas.get(3).addParada(new Paradas("C.C Mayales",rutas.get(3),10.455839,-73.242743));
           rutas.get(3).addParada(new Paradas("Terminal",rutas.get(3),10.438023,-73.245086));
           rutas.get(3).addParada(new Paradas("C.B.C",rutas.get(3),10.406489,-73.236519));


               rutas.add(new Ruta("Coomeva"));
           rutas.get(4).addParada(new Paradas("Coomeva de la 12",rutas.get(4),10.475127,-73.258830));
           rutas.get(4).addParada(new Paradas("Cementos Caribe ",rutas.get(4),10.478531,-73.254055));
           rutas.get(4).addParada(new Paradas("Tienda La Canoa",rutas.get(4),10.480833,-73.244430));
           rutas.get(4).addParada(new Paradas("Entrada 9 de Marzo",rutas.get(4),10.475040,-73.240171));
           rutas.get(4).addParada(new Paradas("4ta con 18 esquina",rutas.get(4),10.475033,-73.240195));
           rutas.get(4).addParada(new Paradas("Colegio Fransisco Molina",rutas.get(4),10.460477,-73.229034));
           rutas.get(4).addParada(new Paradas("Entrada los Cocos",rutas.get(4),10.457772,-73.233034));
           rutas.get(4).addParada(new Paradas("C.C Mayales",rutas.get(4),10.455839,-73.242743));
           rutas.get(4).addParada(new Paradas("Terminal",rutas.get(4),10.438023,-73.245086));
           rutas.get(4).addParada(new Paradas("C.B.C",rutas.get(4),10.406489,-73.236519));


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


