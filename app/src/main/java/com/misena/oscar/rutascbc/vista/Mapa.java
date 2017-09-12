package com.misena.oscar.rutascbc.vista;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.controlador.ControladorRutas;
import com.misena.oscar.rutascbc.modelo.Paradas;
import com.misena.oscar.rutascbc.modelo.Ruta;
import com.misena.oscar.rutascbc.rutas.DirectionFinder;
import com.misena.oscar.rutascbc.rutas.PasarUbicacion;
import com.misena.oscar.rutascbc.rutas.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, PasarUbicacion {

    private GoogleMap mMap;
    ControladorRutas controladorRutas;
    private GoogleApiClient client;
    private Location mLastLocation, paradaLocation;
    List<Paradas> parada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        controladorRutas = new ControladorRutas();
        if (client == null) {
            client = new GoogleApiClient.Builder(Mapa.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    @Override
    protected void onStart() {
        client.connect();
        super.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           return;
        }
//        mMap.setMyLocationEnabled(true);
        String ruta=getIntent().getStringExtra("nombre");
        Ruta rutas=controladorRutas.consultarUnaRuta(ruta);
        parada=rutas.getParadas();
        for (int f=0;f<parada.size();f++){
           mMap.addMarker(new MarkerOptions().position(new LatLng(parada.get(f).getUbicacionLat(),parada.get(f).getUbicacionLon())).title(parada.get(f).getNombreParada()));

        }
        LatLng coordenadas=new LatLng(parada.get(0).getUbicacionLat(),parada.get(0).getUbicacionLon());

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas,16f));
     //   mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(parada.get(0).getUbicacionLat(),parada.get(0).getUbicacionLon())));

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "No tiene permisos para usar la ubicacion del dispositivo.", Toast.LENGTH_SHORT).show();
            return;
        }else {

            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(client);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       if (item.getItemId() == R.id.mostrar_ruta){
           if (mLastLocation != null){

               LatLng origen = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
               LatLng destino = paradaMasCercana();

               DirectionFinder finder = new DirectionFinder(this, origen, destino);
               finder.peticionRutas();
           }else {
               Toast.makeText(this, "No se ha podido determinar tu ubicacion.", Toast.LENGTH_SHORT).show();
           }
        }
        return super.onOptionsItemSelected(item);
    }

    private LatLng paradaMasCercana(){

        ArrayList<Float> arrayDistancias = new ArrayList<>();
        LatLng latLngClosestStop;
        for (Paradas MiParada : parada){

            Location paradaLocation= new Location(LOCATION_SERVICE);
            paradaLocation.setLatitude(MiParada.getUbicacionLat());
            paradaLocation.setLongitude(MiParada.getUbicacionLon());

            float distancia = mLastLocation.distanceTo(paradaLocation);
            arrayDistancias.add(distancia);
        }

        int indiceMenorDistancia = arrayDistancias.indexOf(Collections.min(arrayDistancias));
        latLngClosestStop = new LatLng(parada.get(indiceMenorDistancia).getUbicacionLat()
        , parada.get(indiceMenorDistancia).getUbicacionLon());
        return latLngClosestStop;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void trazarRutas(List<Route> rutas) {
        List<Polyline> polylinePaths = new ArrayList<>();
        Log.e("Trazar rutas","trazando rutas");
        for (Route route : rutas) {
            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));
            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

}
