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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
    List<ModelGaleria> arrayListGaleria;
    AdapterGaleria adapterGaleria;
    ControladorGaleria controladorGaleria;
    ListView listaGale;
    FirebaseStorage storage ;
    StorageReference storageRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refere = database.getReference("rutas");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        listaGale=(ListView)findViewById(R.id.lista_galeria);
        controladorGaleria =new ControladorGaleria();
galeria=new ModelGaleria();
        arrayListGaleria=new ArrayList<>();

        refere.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String ruta=(String)dataSnapshot.child("ruta").getValue();
                final String nombre=(String)dataSnapshot.child("nombre").getValue();
                final String ficha=(String)dataSnapshot.child("ficha").getValue();
                StorageReference imagesRef = storageRef.child(ruta);
                long multi=1024*1024;
                imagesRef.getBytes(multi).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        String fotoGale = Base64.encodeToString(bytes, Base64.DEFAULT);
                        galeria.setFoto(fotoGale);
                        galeria.setNombre(nombre);
                        galeria.setFicha(ficha);
                        galeria.save();
                        mostrarGaleria();
                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    private void mostrarGaleria() {
       arrayListGaleria=controladorGaleria.consultarGaleria();
       adapterGaleria=new AdapterGaleria(arrayListGaleria, this);
        listaGale.setAdapter(adapterGaleria);



    }

    public  void irfotos(View v){
        Intent i =new Intent(Galeria.this,GaleriaAministrador.class);
        startActivity(i);
        finish();

    }


}
