package com.misena.oscar.rutascbc.controlador;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Rutas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/08/2017.
 */

public class ControladorParada {

    public  void llenarParadas(ArrayList<Paradas> parada){

        for (int f=0;f<parada.size();f++){
            Paradas pa=parada.get(f);
            pa.save();
        }
    }
    public Paradas  consultarUnaParada(String nombreParada){


        return null;
    }
}
