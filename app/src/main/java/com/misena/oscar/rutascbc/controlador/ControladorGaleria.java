package com.misena.oscar.rutascbc.controlador;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/08/2017.
 */

public class ControladorGaleria  {
    public List<ModelGaleria> listGaleria;


    public void llenarGaleria(ArrayList<ModelGaleria> galerias) {
        for (int f = 0; f < galerias.size(); f++) {
            ModelGaleria galeria = galerias.get(f);
            galeria.save();
        }
    }

    public List<ModelGaleria> consultarGaleria(){

        return new Select().all().from(ModelGaleria.class).execute();
    }
}
