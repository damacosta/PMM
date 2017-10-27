package com.example.alemen.enviardatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView otroSaludo = (TextView) findViewById(R.id.micontraseña);

        Bundle recogerBundle = getIntent().getExtras();
        otroSaludo.setText(recogerBundle.getString("TEXTO"));
    }
}
