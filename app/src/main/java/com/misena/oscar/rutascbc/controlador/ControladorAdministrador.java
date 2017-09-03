package com.misena.oscar.rutascbc.controlador;

import android.util.Log;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.ModelAdmin;

import java.util.List;

/**
 * Created by user on 03/09/2017.
 */

public class ControladorAdministrador {




    public Boolean admin(){

        List<ModelAdmin> listAdm=new Select().all().from(ModelAdmin.class).execute();
        return listAdm.size()==0;
    }
    public Boolean validarAdm(String usuario,String contrasena){
        boolean adminExiste;
        ModelAdmin modelAdmin=new Select().from(ModelAdmin.class).where("nombre=?",usuario).and("password=?",contrasena).executeSingle();

        if (modelAdmin == null){
            adminExiste = false;

        }else{
            adminExiste = true;
            Log.e("admin", "nombre: " + modelAdmin.getNombre() + " contra: " +modelAdmin.getPassword() );

        }
        return adminExiste;
    }
}
