package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.Usuario;

public class RegistroLogin extends AppCompatActivity {

    EditText nombre,correo,contrasena,repetirContrasena;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        nombre=(EditText)findViewById(R.id.edt_nombre);
        correo=(EditText)findViewById(R.id.edt_correo);
        contrasena=(EditText)findViewById(R.id.edt_contrasena);
        repetirContrasena=(EditText)findViewById(R.id.edt_repetir_contrasena);

    }
    public  void registro(View v){
        String pasword=contrasena.getText().toString();
        String rePasword=repetirContrasena.getText().toString();
        String nombreR=nombre.getText().toString();
        String correoR=correo.getText().toString();
         if (nombreR.isEmpty()&&correoR.isEmpty()&&pasword.isEmpty()&&rePasword.isEmpty()){
             Toast.makeText(this, "Debe LLenar Todos los Campos", Toast.LENGTH_LONG).show();
          }else {

             if (pasword.equals(rePasword)) {
                 usuario = new Usuario(nombre.getText().toString(), correo.getText().toString(), contrasena.getText().toString());

                 usuario.save();
          /*
            */
                 Intent i =new Intent(RegistroLogin.this, Login.class);
                 startActivity(i);
                 finish();
             }else{
                 Toast.makeText(this, "Las Contraseñas No Son Iguales", Toast.LENGTH_LONG).show();
             }

         }



    }



}
