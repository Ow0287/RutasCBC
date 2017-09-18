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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.misena.oscar.rutascbc.modelo.MensajeChat;
import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.adapter.ChatAdapter;
import com.misena.oscar.rutascbc.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    ListView listaChat;
    EditText edtMensajeChat;
    FloatingActionButton btnEnviar;
    DatabaseReference refChat;
    String correo;
    List<MensajeChat> listaMensajes;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listaChat = (ListView)findViewById(R.id.lista);
        edtMensajeChat = (EditText)findViewById(R.id.edt_chat);
        FirebaseDatabase dataBaseFirebase = FirebaseDatabase.getInstance();
        refChat = dataBaseFirebase.getReference("Chat");
        btnEnviar = (FloatingActionButton)findViewById(R.id.enviar);
        SharedPreferences shared =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        String nombre = shared.getString("nombre", "");
        Usuario usuario = new Select().from(Usuario.class).where("nombre = ?", nombre)
                .orderBy("RANDOM()").executeSingle();
        correo = usuario.getCorreo();
        listaMensajes = new ArrayList<>();
        adapter = new ChatAdapter(listaMensajes, Chat.this, correo);
        listaChat.setAdapter(adapter);

        refChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e("key snap", dataSnapshot.getKey());

                MensajeChat nuevoMensaje = new MensajeChat((String)dataSnapshot.child("mensaje").getValue()
                , (String)dataSnapshot.child("usuario").getValue());
                listaMensajes.add(nuevoMensaje);
                adapter.setLista(listaMensajes);
                adapter.notifyDataSetChanged();
                Log.e("mensaje", (String) dataSnapshot.child("mensaje").getValue());
                Log.e("usuario", (String) dataSnapshot.child("usuario").getValue());
                listaChat.setSelection(adapter.getCount() -1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void enviarMensajes(View v){
        //Captura del boton enviar
        if (!edtMensajeChat.getText().toString().equals("")){

            MensajeChat mensaje = new MensajeChat(edtMensajeChat.getText().toString(), correo);
            refChat.push().setValue(mensaje);
            // Aqui se envia el mensaje
            edtMensajeChat.setText("");
        }
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
