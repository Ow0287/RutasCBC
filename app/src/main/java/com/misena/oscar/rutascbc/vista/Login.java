package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.Usuario;

public class Login extends AppCompatActivity {

    EditText nombreE,contrasenaE;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nombreE=(EditText)findViewById(R.id.edt_usuario);
        contrasenaE=(EditText)findViewById(R.id.edt_contrasena_login);
        shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);


    }

    public  void  login(View v){

        RadioButton radioAprendiz  = (RadioButton)findViewById(R.id.radioAprendiz);
        RadioButton radioConductor = (RadioButton)findViewById(R.id.radioConductor);

        String usuarioDigitado = nombreE.getText().toString();
        String claveDigitada = contrasenaE.getText().toString();

        if (usuarioDigitado.equals("") || claveDigitada.equals("")){

            Toast.makeText(this, "Por favor llenar campos faltantes", Toast.LENGTH_SHORT).show();
        }else {
            if (radioAprendiz.isChecked()) {

                Usuario user = new Select().from(Usuario.class).where("nombre = ?", nombreE.getText().toString()).and("contrasena = ?", contrasenaE.getText().toString())
                        .orderBy("RANDOM()").executeSingle();

                if (user != null) {
                    editor = shared.edit();
                    editor.putString("nombre", user.getNombre());
                    editor.putBoolean("login", true);
                    editor.apply();
                } else {

                    Toast.makeText(this, "EL USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
                }
            } else if (radioConductor.isChecked()) {

                checkConductor(usuarioDigitado, claveDigitada);
            }
        }
}

    private void checkConductor(final String usuarioDigitado, final String claveDigitada) {

        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        DatabaseReference refLOgin = dataBase.getReference("Conductor");
        refLOgin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String usuarioDescargado = (String) dataSnapshot.child(usuarioDigitado).child("usuario").getValue();
                String claveDescargada   = (String) dataSnapshot.child(usuarioDigitado).child("clave").getValue();

                // check usuario
                if (usuarioDescargado != null){
                    if (usuarioDescargado.equals(usuarioDigitado)){

                        if (claveDescargada.equals(claveDigitada)){

                            Log.e("Login", "exitoso");
                            editor = shared.edit();
                            editor.putBoolean("inicioSesionUsuario", true);
                            editor.apply();
                            irMenu();

                        }else{

                            Toast.makeText(Login.this, "Contraseña no es valida", Toast.LENGTH_SHORT).show();
                            contrasenaE.requestFocus();
                        }
                    }else{
                        mostrarMensajeConductor();
                    }
                }else{
                    mostrarMensajeConductor();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void irMenu() {
        Intent i =new Intent(Login.this, MenuRutas.class);
        startActivity(i);
        finish();
    }

    private void mostrarMensajeConductor() {

        Toast.makeText(Login.this, "Usuario no es valido", Toast.LENGTH_SHORT).show();
        nombreE.requestFocus();
    }

    public  void  registroLogin(View v){
        Intent i =new Intent(Login.this, RegistroLogin.class);
        startActivity(i);
        finish();

    }


}
