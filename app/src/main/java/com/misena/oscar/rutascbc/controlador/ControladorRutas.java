package com.misena.oscar.rutascbc.controlador;

import com.activeandroid.query.Select;
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
            lu.save();
        }


    }
    public Rutas consultarUnaRuta(String nombre){

        Rutas ruta= new Select().from(Rutas.class).where("nombre = ?", nombre).executeSingle();
        return ruta;
    }


    public List<Rutas>  consultarTodasRutas(){
        List<Rutas> rut = new Select().all().from(Rutas.class).execute();

        return rut;
    }
}
