package com.misena.oscar.rutascbc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.misena.oscar.rutascbc.vista.MenuRutas;

public class Integrantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            startActivity(new Intent(Integrantes.this, MenuRutas.class));
            finish();     }
        return super.onOptionsItemSelected(item);
    }
}
