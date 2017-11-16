package com.example.alemen.examenpmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        final TextView zonas = (TextView) findViewById(R.id.textView2);
        final TextView kilos = (TextView) findViewById(R.id.textView3);
        final TextView detalles = (TextView) findViewById(R.id.textView4);
        final TextView tarifas = (TextView) findViewById(R.id.textView5);
        final TextView precio = (TextView) findViewById(R.id.textView6);


        Bundle recogerBundleZona = getIntent().getExtras();
        zonas.setText(recogerBundleZona.getString("ZONA"));

        Bundle recogerBundleKilos = getIntent().getExtras();
        kilos.setText(recogerBundleKilos.getString("KILOS"));

        Bundle recogerBundleDetalles = getIntent().getExtras();
        detalles.setText(recogerBundleDetalles.getString("DETALLES"));

        Bundle recogerBundleTarifa = getIntent().getExtras();
        tarifas.setText(recogerBundleTarifa.getString("TARIFAS"));

        Bundle recogerBundlePrecio = getIntent().getExtras();
        precio.setText(recogerBundlePrecio.getString("PRECIO"));
    }
}
