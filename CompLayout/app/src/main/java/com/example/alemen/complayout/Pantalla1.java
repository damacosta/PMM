package com.example.alemen.complayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pantalla1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);
        final ImageButton botonPrueba = (ImageButton) findViewById(R.id.botonPrueba);

        final TextView etiquetaPrueba = (TextView) findViewById(R.id.etiquetaPrueba);

        botonPrueba.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                etiquetaPrueba.setText("HOLA");
            }
        });
    }
}
