package com.misena.oscar.rutascbc.rutas;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DirectionFinder {

    public PasarUbicacion pasar;
    public LatLng origen;
    public LatLng destino;
    //private final String API_KEY = "AIzaSyCl1cDkUtCTtlivZd4MBo1v68j5EDFAAOo";

    private final String API_KEY = "AIzaSyBdvMKBvGKMFUTC0cdtx8u-lSjPCLsXQq4";
    private final String URL_HTTP = "https://maps.googleapis.com/maps/api/directions/json?origin=";
            ;
//"https://maps.googleapis.com/maps/api/directions/json?origin=10.451655,-73.246534&destination=10.464147,-73.243916&key=AIzaSyDbCCm75pd-bS-UIuSxRADAmnyY62fyeNk"
    public DirectionFinder(PasarUbicacion pasar, LatLng origen, LatLng destino) {
        this.pasar = pasar;
        this.origen = origen;
        this.destino = destino;
        Log.e("finder","creado");
    }
    public void peticionRutas(){
        String link = crearUrl();
        //String myurl = "https://maps.googleapis.com/maps/api/directions/json?origin=10.451655,-73.246534&destination=10.464147,-73.243916&key=AIzaSyDbCCm75pd-bS-UIuSxRADAmnyY62fyeNk";
        Log.e("peticion","rutas");
        new DownloadRawData().execute(link);
    }

    private String crearUrl(){
        return URL_HTTP + origen.latitude +","+origen.longitude+"&destination="+destino.latitude+","+destino.longitude+"&key="+API_KEY;
    }



    private class DownloadRawData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            StringBuffer buffer = new StringBuffer();
            try {
                Log.e("entro al try","ok");
                URL url = new URL(params[0]);
                InputStream is = url.openConnection().getInputStream();
                Log.e("se abrio la ","conexion");

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                Log.e("json: ",buffer.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String res) {
            try {
                parseJSon(res);
                Log.e("ir a parse","JSON");
            } catch (JSONException e) {
                Log.e("Exception","con JSON");
                e.printStackTrace();
            }
        }
    }

    private void parseJSon(String data) throws JSONException {
        if (data == null)
            return;

        List<Route> routes = new ArrayList<>();
        JSONObject jsonData = new JSONObject(data);
        JSONArray jsonRoutes = jsonData.getJSONArray("routes");
        for (int i = 0; i < jsonRoutes.length(); i++) {
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
            Route route = new Route();

            JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
            JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
            JSONObject jsonLeg = jsonLegs.getJSONObject(0);
            JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
            JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
            JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
            JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

            route.distance = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value"));
            route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"));
            route.endAddress = jsonLeg.getString("end_address");
            route.startAddress = jsonLeg.getString("start_address");
            route.startLocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
            route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"));
            route.points = decodePolyLine(overview_polylineJson.getString("points"));
            Log.e("Duracion ",route.duration.text);
            routes.add(route);
        }
        pasar.trazarRutas(routes);
     //   listener.onDirectionFinderSuccess(routes);
    }

    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }

        return decoded;
    }
}
