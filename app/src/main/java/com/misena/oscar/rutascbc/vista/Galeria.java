package com.misena.oscar.rutascbc.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refere = database.getReference("");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        listaGale=(ListView)findViewById(R.id.lista_galeria);
        controladorGaleria =new ControladorGaleria();
        galeria=new ModelGaleria();
        arrayListGaleria=new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("ing.odvega@gmail.com", "12345678").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("inicio ","completo");
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    private void aux(DataSnapshot data) {

        new Delete().from(ModelGaleria.class).execute();

        for (DataSnapshot dataSnapshot : data.getChildren()){
            Log.e("for","ruta");
            String ruta = (String) dataSnapshot.child("ruta").getValue();
            final String nombre = (String) dataSnapshot.child("nombre").getValue();
            final String ficha = (String) dataSnapshot.child("ficha").getValue();
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
        }
        mostrarGaleria();

    }
    private void mostrarGaleria() {
        arrayListGaleria = controladorGaleria.consultarGaleria();
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
