package com.misena.oscar.rutascbc.vista;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.controlador.ControladorGaleria;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GaleriaAministrador extends AppCompatActivity {
    EditText nombre, ficha;
    ImageView imageView;
    Bitmap bitmap;
    ControladorGaleria controladorGaleria;
    byte[] b;
//    FirebaseStorage storage ;
//    StorageReference storageRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_aministrador);
        nombre = (EditText) findViewById(R.id.edt_titulacion);
        ficha = (EditText) findViewById(R.id.edt_ficha);
        imageView = (ImageView) findViewById(R.id.img_tarjeta);
        controladorGaleria = new ControladorGaleria();
  //      storage = FirebaseStorage.getInstance();
    //    storageRef = storage.getReference();
    }

    public void fotos(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) ;
        {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            bitmap = foto;

            imageView.setImageBitmap(foto);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
           b= baos.toByteArray();

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public void llenarGaleria() {
        int i = 0;



        //String fotoPro = Base64.encodeToString(b, Base64.DEFAULT);

       // ModelGaleria galer = new ModelGaleria(nombre.getText().toString(), ficha.getText().toString(), fotoPro);
        // controladorGaleria.llenarGaleria();


    }
    public void registrarTitulacion(View view){
        String nombreF=nombre.getText().toString();
        String numFicha=ficha.getText().toString();
/*
        StorageReference imagesRef = storageRef.child("images/"+numFicha);

        UploadTask task = imagesRef.putBytes(b);
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GaleriaAministrador.this, "No se pudo subir la foto.", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(GaleriaAministrador.this, "Foto Subida Satisfactoriamente", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(GaleriaAministrador.this,Galeria.class);
                startActivity(i);

            }
        });
*/


    }
}

