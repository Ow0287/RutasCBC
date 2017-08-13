package com.misena.oscar.rutascbc.controlador;

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
        List<Paradas> parada= Paradas.find(Paradas.class,"nombre = ? ",nombreParada);

        return parada.get(0);
    }


    public List<Paradas>  consultarTodasParadas(String nombreRuta){
        List<Paradas> para= Paradas.find(Paradas.class,"nombre = ?",nombreRuta);
        return null;
    }
}
