package com.misena.oscar.rutascbc.vista;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ControladorRutas controladorRutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        controladorRutas =new ControladorRutas();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String ruta=getIntent().getStringExtra("nombre");
        Ruta rutas=controladorRutas.consultarUnaRuta(ruta);
        List<Paradas> parada=rutas.getParadas();
        for (int f=0;f<parada.size();f++){
           mMap.addMarker(new MarkerOptions().position(new LatLng(parada.get(f).getUbicacionLat(),parada.get(f).getUbicacionLon())).title(parada.get(f).getNombreParada()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(parada.get(0).getUbicacionLat(),parada.get(0).getUbicacionLon())));

    }
}
