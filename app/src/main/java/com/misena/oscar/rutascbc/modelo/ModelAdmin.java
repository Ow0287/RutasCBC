package com.misena.oscar.rutascbc.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 21/08/2017.
 */
@Table(name = "administrador")
public class ModelAdmin extends Model {
    @Column(name = "nombre")
    public String nombre;
    @Column(name = "password")
    public String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ModelAdmin(String nombre, String password) {

        this.nombre = nombre;
        this.password = password;
    }
}
