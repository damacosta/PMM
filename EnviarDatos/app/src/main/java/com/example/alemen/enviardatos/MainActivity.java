package com.example.alemen.enviardatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button miBoton = (Button) findViewById(R.id.generar);
        final EditText miTexto = (EditText) findViewById(R.id.numrandom);

        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numero;
                numero = (int) (Math.random() * 1000000000) + 1;
                String numeroS = Integer.toString(numero);
                miTexto.setText(numeroS);

            }
        });

        final Button botonpasar = (Button) findViewById(R.id.pasar);
        botonpasar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent = new Intent(MainActivity.this, Main2Activity.class);

                Bundle miBundle = new Bundle();
                String mensajePaso = "Tu contraseña es " + miTexto.getText();

                miBundle.putString("TEXTO", mensajePaso);
                miIntent.putExtras(miBundle);

                startActivity(miIntent);
            }
        });
    }
}
