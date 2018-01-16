package com.example.alemen.pruebabd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_cliente);

        Bundle miBundle = getIntent().getExtras();
        Cliente cliente= (Cliente) miBundle.getSerializable("NOMBRE");

        TextView nombre = findViewById(R.id.nombre);
        TextView telefono = findViewById(R.id.telefono);

        nombre.setText("Nombre: " + cliente.getNombre());
        telefono.setText("Telefono: " + cliente.getTelf());
    }
}
