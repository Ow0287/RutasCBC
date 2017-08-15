package com.misena.oscar.rutascbc.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by user on 13/08/2017.
 */
@Table(name = "rutas")
public class Rutas extends Model {

    @Column(name = "nombre")
    public String nombre;

    public Rutas(String nombre, String ubicacionLat, String ubicacionLon) {
        this.nombre = nombre;
    }

    public Rutas() {
    }

    public List<Paradas> getParadas(){
        return getMany(Paradas.class, "ruta_parada");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
