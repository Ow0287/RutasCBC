package com.misena.oscar.rutascbc.vista;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.misena.oscar.rutascbc.R;

public class MenuRutas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private boolean usuarioChofer;
    private SharedPreferences shared;
    DatabaseReference refLOgin;
    private GoogleApiClient client;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shared = getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        usuarioChofer = shared.getBoolean("inicioSesionUsuario", false);

        String cedulaConductor = shared.getString("usuarioConductor", "usuario");

        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        refLOgin = dataBase.getReference("Conductor/" + cedulaConductor);



        if (client == null) {
            client = new GoogleApiClient.Builder(MenuRutas.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        client.connect();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu2) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(MenuRutas.this);
            dialogo.setMessage("Desea Cerrar Sesion");
            dialogo.setTitle("Cerrar Sesion");
            dialogo.setNeutralButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogo();

                }
            });
            dialogo.create();
            dialogo.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        if (!usuarioChofer) {

            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                Intent intent = new Intent(MenuRutas.this, Rutas.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.nav_gallery) {
                Intent intent = new Intent(MenuRutas.this, Galeria.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.nav_slideshow) {
                Intent intent = new Intent(MenuRutas.this, Chat.class);
                startActivity(intent);
                finish();

            } else if (id == R.id.nav_manage) {
                finish();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } else {
            Toast.makeText(this, "Usted no tiene accesso a esta opcion.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void dialogo() {
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("login", false);
        editor.putBoolean("inicioSesionUsuario", false);
        editor.apply();

        Intent intent = new Intent(MenuRutas.this, Login.class);
        startActivity(intent);
        finish();


    }

    @Override
    protected void onDestroy() {

        DatabaseReference.goOffline();
        client.disconnect();
        super.onDestroy();
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
           /*
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(client);
            */
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(15000);
            mLocationRequest.setFastestInterval(10000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(client, mLocationRequest, new com.google.android.gms.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

               actualizarPosicion(location);

                }
            });
        }
    }

    private void actualizarPosicion(Location location) {

        if (location != null){
            refLOgin.child("latitud").setValue(location.getLatitude());
            refLOgin.child("longitud").setValue(location.getLongitude());
        }else{
            Log.e("la location", "es NULL");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
