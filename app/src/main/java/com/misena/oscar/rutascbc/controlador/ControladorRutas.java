package com.misena.oscar.rutascbc.controlador;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.Rutas;

import java.util.ArrayList;
import java.util.List;

public class ControladorRutas {

    public  void llenarSitios(ArrayList<Rutas> rutas){
        for (int f=0;f<rutas.size();f++){
            Rutas lu=rutas.get(f);
            lu.save();
        }


    }

    public Rutas consultarUnaRuta(String nombre){

        return new Select().from(Rutas.class).where("nombre = ?", nombre).executeSingle();
    }

    public List<Rutas>  consultarTodasRutas(){

        return new Select().all().from(Rutas.class).execute();
    }
}
