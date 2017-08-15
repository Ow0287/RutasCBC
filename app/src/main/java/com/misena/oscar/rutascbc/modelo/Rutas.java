package com.misena.oscar.rutascbc.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.misena.oscar.rutascbc.controlador.ControladorParada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13/08/2017.
 */
@Table(name = "rutas")
public class Rutas extends Model {

    private ControladorParada controladorParada;
    public ArrayList<Paradas> listParadas;
    @Column(name = "nombre")
    public String nombre;

    public Rutas(String nombre) {
        this.nombre = nombre;
        controladorParada = new ControladorParada();
        listParadas = new ArrayList<>();
    }

    public Rutas() {
    }

    public List<Paradas> getParadas(){
        return getMany(Paradas.class, "ruta_parada");
    }

    public void addParada(Paradas paradas){
        this.listParadas.add(paradas);
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
