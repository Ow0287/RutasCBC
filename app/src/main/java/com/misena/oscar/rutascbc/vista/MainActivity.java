package com.misena.oscar.rutascbc.vista;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.misena.oscar.rutascbc.R;
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
        if (controladorRutas.consultarTodasRutas("Hoteles").size() == 0) {


            //Rutas:

            rutas.add(new Rutas("Cuarta con 21 y 28",  "2.447865", "-76.606308"));


            controladorRutas.llenarSitios(rutas);

        }

    }
}