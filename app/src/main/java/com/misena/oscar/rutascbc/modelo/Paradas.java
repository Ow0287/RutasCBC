package com.misena.oscar.rutascbc.modelo;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 13/08/2017.
 */
@Table(name = "parada")
public class Paradas extends Model{

    @Column(name = "nombre")
    public   String nombreParada;
    @Column(name = "ruta_parada")
    public Rutas ruta;
    @Column(name = "latitud")
    public   double ubicacionLat;
    @Column(name = "longitud")
    public   double ubicacionLon;

    public Paradas(String nombreParada, Rutas ruta, double ubicacionLat, double ubicacionLon) {
        this.nombreParada = nombreParada;
        this.ruta = ruta;
        this.ubicacionLat = ubicacionLat;
        this.ubicacionLon = ubicacionLon;
    }

    public Paradas() {
    }

    public String getNombreParada() {
        return nombreParada;
    }

    public void setNombreParada(String nombreParada) {
        this.nombreParada = nombreParada;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }

    public double getUbicacionLat() {
        return ubicacionLat;
    }

    public void setUbicacionLat(double ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    public double getUbicacionLon() {
        return ubicacionLon;
    }

    public void setUbicacionLon(double ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }
}
