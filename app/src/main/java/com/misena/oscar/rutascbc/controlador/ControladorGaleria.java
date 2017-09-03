package com.misena.oscar.rutascbc.controlador;

import android.util.Log;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;

import java.util.ArrayList;
import java.util.List;

public class ControladorGaleria  {

    public List<ModelGaleria> listGaleria;

    public void llenarGaleria(ArrayList<ModelGaleria> galerias) {
        for (int f = 0; f < galerias.size(); f++) {
            ModelGaleria galeria = galerias.get(f);
            galeria.save();
        }
    }

    public List<ModelGaleria> consultarGaleria(){

        return new Select().all().from(ModelGaleria.class).where("sincronizado = ?", true).execute();
    }

    public boolean consultarModelGaleria(String nombre){

        boolean modelo = true;
        ModelGaleria model = new Select().from(ModelGaleria.class)
                .where("nombre = ?", nombre).executeSingle();

        if (model == null){
            Log.e("modelo nulo","no existe");
            modelo = false;
        }else {
            Log.e("ficha modelo ", model.getFicha());
        }

        /*
        if (model.getNombre() == null){
            modelo = false;
        }*/
        return modelo;
    }


}
