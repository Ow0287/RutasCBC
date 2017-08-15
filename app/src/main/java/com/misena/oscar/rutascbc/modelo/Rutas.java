package com.misena.oscar.rutascbc.modelo;

/**
 * Created by user on 13/08/2017.
 */

public class Rutas {
    private  String nombre;
    private  String ubicacionLat ,ubicacionLon;


    public Rutas(String nombre, String ubicacionLat, String ubicacionLon) {
        this.nombre = nombre;
        this.ubicacionLat = ubicacionLat;
        this.ubicacionLon = ubicacionLon;
    }

    public Rutas() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacionLat() {
        return ubicacionLat;
    }

    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    public String getUbicacionLon() {
        return ubicacionLon;
    }

    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }
}
