package com.misena.oscar.rutascbc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.misena.oscar.rutascbc.modelo.Usuario;
import com.misena.oscar.rutascbc.vista.Login;

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

        if (pasword.equals(rePasword)) {
            usuario = new Usuario(nombre.getText().toString(), correo.getText().toString(), contrasena.getText().toString());

            SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor =shared.edit();
            editor.putString("nombre",usuario.getNombre());
            editor.putString("correo",usuario.getCorreo());
            editor.putString("contrasena",usuario.getContrasena());
            editor.apply();
            Intent i =new Intent(RegistroLogin.this, Login.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "El Usuario Ya Existe", Toast.LENGTH_LONG).show();
        }

    }


}
