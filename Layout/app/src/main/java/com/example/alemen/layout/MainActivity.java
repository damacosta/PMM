package com.example.alemen.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botonpantalla1 = (Button) findViewById(R.id.button1);
        botonpantalla1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla1.class);
                startActivity(miIntent1);
            }
        });

        final Button botonpantalla2 = (Button) findViewById(R.id.button2);
        botonpantalla2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla2.class);
                startActivity(miIntent1);
            }
        });

        final Button botonpantalla3 = (Button) findViewById(R.id.button3);
        botonpantalla3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla3.class);
                startActivity(miIntent1);
            }
        });

        final Button botonpantalla4 = (Button) findViewById(R.id.button4);
        botonpantalla4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla4.class);
                startActivity(miIntent1);
            }
        });
    }
}