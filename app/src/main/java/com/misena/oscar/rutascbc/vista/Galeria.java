package com.misena.oscar.rutascbc.vista;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.Ruta;

public class Galeria extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        imageView = (ImageView) findViewById(R.id.img_tarjeta);

    }
    public  void fotos(View v){
        Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && resultCode== Activity.RESULT_OK);{
            Bitmap foto=(Bitmap)data.getExtras().get("data");
            bitmap = foto;

            imageView.setImageBitmap(foto);

        }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
