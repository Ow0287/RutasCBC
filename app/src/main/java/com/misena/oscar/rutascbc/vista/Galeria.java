package com.misena.oscar.rutascbc.vista;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.adapter.AdapterGaleria;
import com.misena.oscar.rutascbc.controlador.ControladorGaleria;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;
import com.misena.oscar.rutascbc.modelo.Ruta;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class Galeria extends AppCompatActivity {
    ModelGaleria galeria;
ArrayList<Galeria> arrayListGaleria;
    AdapterGaleria adapterGaleria;
    ControladorGaleria controladorGaleria;
    ListView listaGale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
//adapterGaleria =new A
        adapterGaleria=new AdapterGaleria();
       listaGale=(ListView)findViewById(R.id.lista_galeria);
        listaGale.setAdapter(adapterGaleria);




        controladorGaleria =new ControladorGaleria();
        //controladorGaleria.llenarGaleria(listaGale);

    }

    public  void irfotos(View v){
        Intent i =new Intent(Galeria.this,GaleriaAministrador.class);
        startActivity(i);
        finish();

    }


}
