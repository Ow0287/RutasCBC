package com.misena.oscar.rutascbc.controlador;

import com.misena.oscar.rutascbc.modelo.Rutas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/08/2017.
 */

public class ControladorRutas {
    public  void llenarSitios(ArrayList<Rutas> rutas){
        for (int f=0;f<rutas.size();f++){
            Rutas lu=rutas.get(f);
       //     lu.save();



        }


    }
    public Rutas  consultarUnaRuta(String nombre){
        List<Rutas> ruta= Rutas.find(Rutas.class,"nombre = ? ",nombre);

        return ruta.get(0);
    }


    public List<Rutas>  consultarTodasRutas(String nombre){
        List<Rutas> rut= Rutas.find(Rutas.class,"tipo = ?",nombre);

        return rut;
    }
}
