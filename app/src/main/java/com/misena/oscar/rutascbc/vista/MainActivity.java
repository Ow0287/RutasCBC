package com.misena.oscar.rutascbc.vista;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.RegistroLogin;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.Rutas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Rutas> rutas = new ArrayList<>();
       ControladorRutas controladorRutas = new ControladorRutas();
     //   if (controladorRutas.consultarTodasRutas("Rutas").size() == 0) {


            //Rutas:

            rutas.add(new Rutas("Cuarta con 21 y 28",  "2.447865", "-76.606308"));


            controladorRutas.llenarSitios(rutas);

//        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the MenuRutas-Activity. */
                Intent mainIntent = new Intent(MainActivity.this,RegistroLogin.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 5000);

    }
}