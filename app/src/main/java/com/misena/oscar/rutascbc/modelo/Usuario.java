package com.misena.oscar.rutascbc.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 13/08/2017.
 */

@Table(name = "usuario")
public class Usuario extends Model{

    @Column(name = "nombre")
    public String nombre;
    @Column(name = "correo")
    public String correo;
    @Column(name = "contrasena")
    public String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
