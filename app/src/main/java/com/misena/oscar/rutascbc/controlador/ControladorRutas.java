package com.misena.oscar.rutascbc.controlador;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;
import java.util.List;

public class ControladorRutas {

    public  void llenarSitios(ArrayList<Ruta> rutas){
        for (int f=0;f<rutas.size();f++){
            Ruta ru=rutas.get(f);
            ru.save();
        }


    }

    public Ruta consultarUnaRuta(String nombre){

        return new Select().from(Ruta.class).where("nombre = ?", nombre).executeSingle();
    }

    public List<Ruta>  consultarTodasRutas(){

        return new Select().all().from(Ruta.class).execute();
    }
}
