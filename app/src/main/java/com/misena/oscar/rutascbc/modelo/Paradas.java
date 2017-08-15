package com.misena.oscar.rutascbc.modelo;



/**
 * Created by user on 13/08/2017.
 */

public class Paradas{

    private  String nombreParada,nombreRuta;
    private  String ubicacionLat ,ubicacionLon;

    public Paradas(String nombreParada, String nombreRuta, String ubicacionLat, String ubicacionLon) {
        this.nombreParada = nombreParada;
        this.nombreRuta = nombreRuta;
        this.ubicacionLat = ubicacionLat;
        this.ubicacionLon = ubicacionLon;
    }

    public String getNombreParada() {
        return nombreParada;
    }

    public void setNombreParada(String nombreParada) {
        this.nombreParada = nombreParada;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
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
