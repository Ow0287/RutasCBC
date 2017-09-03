package com.misena.oscar.rutascbc.controlador;

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
        ModelAdmin modelAdmin=new Select().from(ModelAdmin.class).where("nombre=?",usuario).and("password=?",contrasena).executeSingle();

        return modelAdmin==null;
    }
}
