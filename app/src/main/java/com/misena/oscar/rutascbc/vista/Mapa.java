package com.misena.oscar.rutascbc.vista;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private Location mLastLocation;
    List<Paradas> parada;
    SharedPreferences shared;
    DatabaseReference refRutaConductor, refConductor;
    String ruta;
    Marker busMarker;

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
        shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        ruta=getIntent().getStringExtra("nombre");

        refRutaConductor = FirebaseDatabase.getInstance().getReference("RutaConductor");
        refRutaConductor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                    Log.e("key snapshot", dataSnapshot.getKey());
                    Log.e("cedula conductor", (String) dataSnapshot.child(ruta).getValue());

                    actualizarBus((String) dataSnapshot.child(ruta).getValue());
                }catch (Exception e){

                    Toast.makeText(Mapa.this, "No hay bus en esta ruta", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void actualizarBus(String value) {

        Log.e("en ", "actualizarBus");
        DatabaseReference bus = FirebaseDatabase.getInstance()
                .getReference("Conductor").child(value);

        bus.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Log.e("chlid","changed");
                Log.e("snapshot", dataSnapshot.getKey());
                //Crear nuevo marcador en mapa
                try{

                    Double latBus = (Double)dataSnapshot.child("latitud").getValue();
                    Double lngBus = (Double)dataSnapshot.child("longitud").getValue();

                    LatLng busLatLng = new LatLng(latBus, lngBus);

                    if (busMarker == null){

                        busMarker = mMap.addMarker(new MarkerOptions().position(busLatLng).title("Bus")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        );
                        busMarker.showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(busLatLng,16f));

                    }else{

                        busMarker.setPosition(busLatLng);
                        busMarker.showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(busLatLng,16f));

                    }

                }catch(Exception e){

                    e.printStackTrace();
                    Toast.makeText(Mapa.this, "Bus no esta activo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
        mMap.setMyLocationEnabled(true);

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

            for (int i = 0; i < parada.size(); i++){
                if (i != parada.size()-1){
                    LatLng origen = new LatLng(parada.get(i).getUbicacionLat(), parada.get(i).getUbicacionLon());
                    LatLng destino = new LatLng(parada.get(i+1).getUbicacionLat(), parada.get(i+1).getUbicacionLon());

                    DirectionFinder finder = new DirectionFinder(this, origen, destino);
                    finder.peticionRutas();
                }else{
                    break;
                }
        }
        }else if(item.getItemId() == android.R.id.home){

            startActivity(new Intent(Mapa.this, Rutas.class));
            finish();
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
    protected void onDestroy() {

        DatabaseReference.goOffline();
        client.disconnect();
        super.onDestroy();
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
