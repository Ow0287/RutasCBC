package com.misena.oscar.rutascbc;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ejem e=new Ejem("OSCAR", "VEGA");

      try {
          e.save();
          Toast.makeText(this, "guardado exitosamente", Toast.LENGTH_SHORT).show();

      }catch (SQLException ex){
          Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
      }

    }

}
