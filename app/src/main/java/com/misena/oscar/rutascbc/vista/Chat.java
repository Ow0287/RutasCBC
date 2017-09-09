package com.misena.oscar.rutascbc.vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.misena.oscar.rutascbc.R;

public class Chat extends AppCompatActivity {

    ListView listaChat;
    EditText edtMensajeChat;
    FloatingActionButton btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
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
