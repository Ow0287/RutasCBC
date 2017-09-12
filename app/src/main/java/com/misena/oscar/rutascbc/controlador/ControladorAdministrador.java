package com.misena.oscar.rutascbc.controlador;

import android.util.Log;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.modelo.ModelAdmin;

import java.util.List;

public class ControladorAdministrador {

    public Boolean admin(){

        List<ModelAdmin> listAdm=new Select().all().from(ModelAdmin.class).execute();
        return listAdm.size()==0;
    }
    public Boolean validarAdm(String usuario, String contrasena){
        Log.e("var que llega", usuario);
        boolean adminExiste;
        ModelAdmin modelAdmin=new Select().from(ModelAdmin.class)
                .where("nombre = ?",usuario).orderBy("RANDOM()").executeSingle();

        Log.e("validando", "admin");
        if (modelAdmin == null){
            adminExiste = false;
            Log.e("admin", "ES NULO");
        }else{
            adminExiste = true;
            Log.e("admin", "nombre: " + modelAdmin.getNombre() + " contra: " +modelAdmin.getPassword() );

        }
        return adminExiste;
    }
}
