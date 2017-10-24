package com.example.alemen.complayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        final Button botonpantalla5 = (Button) findViewById(R.id.button5);
        botonpantalla5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla5.class);
                startActivity(miIntent1);
            }
        });

        final Button botonpantalla6 = (Button) findViewById(R.id.button6);
        botonpantalla6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Pantalla6.class);
                startActivity(miIntent1);
            }
        });

        final Button botoncalculadora = (Button) findViewById(R.id.button7);
        botoncalculadora.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent1 = new Intent(MainActivity.this, Calculadora.class);
                startActivity(miIntent1);
            }
        });

    }
}
