package com.misena.oscar.rutascbc.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.misena.oscar.rutascbc.MensajeChat;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.Usuario;

import java.util.List;

public class Chat extends AppCompatActivity {

    ListView listaChat;
    EditText edtMensajeChat;
    FloatingActionButton btnEnviar;
    DatabaseReference refChat;
    String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listaChat = (ListView)findViewById(R.id.lista);
        FirebaseDatabase dataBaseFirebase = FirebaseDatabase.getInstance("Chat");
        refChat = dataBaseFirebase.getReference();
        btnEnviar = (FloatingActionButton)findViewById(R.id.enviar);
        SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        String nombre = shared.getString("nombre", "");
        Usuario usuario = new Select().from(Usuario.class).where("nombre = ?", nombre)
                .orderBy("RANDOM()").executeSingle();
        correo = usuario.getCorreo();
        Log.e("correo", correo);
        cargarMensajes();

    }

    public void enviarMensajes(View v){
        if (!edtMensajeChat.getText().toString().equals("")){

            MensajeChat mensaje = new MensajeChat(edtMensajeChat.getText().toString(), correo);
            refChat.push().setValue(mensaje);

        }
    }

    private void cargarMensajes() {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            startActivity(new Intent(Chat.this, MenuRutas.class));
            finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
