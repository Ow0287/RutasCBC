package com.misena.oscar.rutascbc.vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.misena.oscar.rutascbc.R;

import java.util.List;

public class Chat extends AppCompatActivity {

    ListView listaChat;
    EditText edtMensajeChat;
    FloatingActionButton btnEnviar;
    DatabaseReference refChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listaChat = (ListView)findViewById(R.id.lista);
        FirebaseDatabase dataBaseFirebase = FirebaseDatabase.getInstance("Chat");
        btnEnviar = (FloatingActionButton)findViewById(R.id.enviar);
        cargarMensajes();
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
