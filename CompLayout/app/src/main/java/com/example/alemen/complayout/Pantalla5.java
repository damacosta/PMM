package com.example.alemen.complayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Pantalla5 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla5);

        final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                lblMensaje.setText("ID opción seleccionada: " + checkedId);
            }
        });
    }
}