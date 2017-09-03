package com.misena.oscar.rutascbc.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 16/08/2017.
 */
@Table(name = "galerias")
public class ModelGaleria extends Model{
    @Column(name = "nombre")
    public String nombre;
    @Column(name = "ficha")
    public String ficha;

    @Column(name = "sincronizado")
    public boolean sincronizado;

    public ModelGaleria() {
    }

    @Column(name = "foto")
    public String foto;

    public ModelGaleria(String ficha, String nombre, String foto) {
        this.ficha = ficha;
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
