package com.misena.oscar.rutascbc.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.adapter.AdapterGaleria;
import com.misena.oscar.rutascbc.controlador.ControladorGaleria;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;

import java.util.ArrayList;
import java.util.List;

public class Galeria extends AppCompatActivity {
    ModelGaleria galeria;
    List<ModelGaleria> arrayListGaleria;
    AdapterGaleria adapterGaleria;
    ControladorGaleria controladorGaleria;
    ListView listaGale;
    FirebaseStorage storage ;
    StorageReference storageRef ;
    DatabaseReference refere;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refere = database.getReference("");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        listaGale=(ListView)findViewById(R.id.lista_galeria);
        controladorGaleria =new ControladorGaleria();
        galeria=new ModelGaleria();
        arrayListGaleria=new ArrayList<>();
        arrayListGaleria = controladorGaleria.consultarGaleria();

        mostrarGaleria();

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("ing.odvega@gmail.com", "12345678").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("inicio ","completo");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        refere = refere.child("rutas");
        refere.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("new ", "snapshot");
                Log.e("snapshot:" , dataSnapshot.getKey());

                aux(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void aux(DataSnapshot data) {

        //new Delete().from(ModelGaleria.class).execute();

        for (DataSnapshot dataSnapshot : data.getChildren()) {
            Log.e("for", "ruta");
            String ruta = (String) dataSnapshot.child("ruta").getValue();
            final String nombre = (String) dataSnapshot.child("nombre").getValue();
            final String ficha = (String) dataSnapshot.child("ficha").getValue();

            if (controladorGaleria.consultarModelGaleria(nombre)) {

/*
                StorageReference imagesRef = storageRef.child(ruta);
                long multi = 1024 * 1024;
                imagesRef.getBytes(multi).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        String fotoGale = Base64.encodeToString(bytes, Base64.DEFAULT);
                        galeria.setFoto(fotoGale);
                        galeria.setNombre(nombre);
                        galeria.setFicha(ficha);
                        galeria.save();
                        Log.e("descargar","fotos success");
                    }
                });
  */
            }

            arrayListGaleria = controladorGaleria.consultarGaleria();
            if (arrayListGaleria.size() > 0){
                mostrarGaleria();
            }

            }
        }

    private void sincronizar(){


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.sincronizar){

            sincronizar();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarGaleria() {

        Log.e("tamaño","galeria es " + String.valueOf(arrayListGaleria.size()));
        adapterGaleria=new AdapterGaleria(arrayListGaleria, this);
        listaGale.setAdapter(adapterGaleria);

    }

    public  void validarAdmin(View v){

        final AlertDialog.Builder dialogo=new AlertDialog.Builder(Galeria.this);
        dialogo.setMessage("Acceso para Administrador");
        dialogo.setTitle("Administrador");
        dialogo.setNeutralButton("Entrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent =new Intent(Galeria.this,GaleriaAministrador.class);
                startActivity(intent);
                finish();
            }

        });
        final  EditText admin=new EditText((Galeria.this));
        final  EditText password=new EditText((Galeria.this));
        LinearLayout linearL =new LinearLayout(Galeria.this);
        linearL.setOrientation(LinearLayout.VERTICAL);

        admin.setHint("Administrador");
        password.setHint("Contraseña");
        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearL.addView(admin);
        linearL.addView(password);
        dialogo.setView(linearL);
        dialogo.create();
        dialogo.show();
        /*
        Intent i =new Intent(Galeria.this,GaleriaAministrador.class);
        startActivity(i);
        finish();
*/
    }

}
