package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.Usuario;

public class Login extends AppCompatActivity {

    EditText nombreE,contrasenaE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        nombreE=(EditText)findViewById(R.id.edt_usuario);
        contrasenaE=(EditText)findViewById(R.id.edt_contrasena_login);
    }
    public  void  login(View v){
        String nombre,contrasena;

        Usuario user = new Select().from(Usuario.class).where("nombre = ?", nombreE.getText().toString()).and("contrasena = ?", contrasenaE.getText().toString())
                .orderBy("RANDOM()").executeSingle();

       // nombre=shared.getString("nombre","");
       // contrasena=shared.getString("contrasena","");

        if(user != null)
        {
            SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor =shared.edit();
            editor.putString("nombre", user.getNombre());
            editor.putBoolean("login",true);
            editor.apply();
            Intent i =new Intent(Login.this, MenuRutas.class);
            startActivity(i);
            finish();

        }else {

            Toast.makeText(this, "EL USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
        }

    }
    public  void  registroLogin(View v){
        Intent i =new Intent(Login.this, RegistroLogin.class);
        startActivity(i);
        finish();

    }


}
