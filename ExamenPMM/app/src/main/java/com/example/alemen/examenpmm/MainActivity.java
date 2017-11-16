package com.example.alemen.examenpmm;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strMessage = "";
    Spinner miSpinner;
    CheckBox regalo;
    CheckBox tarjeta;
    private RadioButton TNormal;
    private RadioButton TUrgente;
    double precio = 0;
    String PrecioTotal = "";
    String tarifaMsg = "";

    final static String zona[] = { "Zona A: Asia y Oceanía: 30 €",
            "Zona B: América y África 20 €",
            "Zona C: Europa 10 €"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, zona);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Zona seleccionada: " + zona[position];
                showToast(mensaje);
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final EditText kilosnumero = (EditText) findViewById(R.id.kilos);
        //Borrar texto al hacer click
        kilosnumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kilosnumero.setText("");
            }
        });


        TNormal = (RadioButton)findViewById(R.id.TNormal);
        TUrgente = (RadioButton)findViewById(R.id.TUrgente);
        final Spinner zona = (Spinner) findViewById(R.id.spinner1);
        final Button botonpasar = (Button) findViewById(R.id.button1);
        //Al pulsar el botón calcular hace todo esto
        botonpasar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent (MainActivity.this, resultado.class);

                //Para recoger la zona seleccionada
                Bundle miBundle = new Bundle();
                String mensajeSpinner = "" + zona.getSelectedItem();
                if (mensajeSpinner == "Zona A: Asia y Oceanía: 30 €") {
                    precio = precio + 30;
                }

                miBundle.putString("ZONA", mensajeSpinner);

                //Para recoger los kilos del paquete
                Bundle miBundle2 = new Bundle();
                String numeroKilos = "" + kilosnumero.getText();
                miBundle2.putString("KILOS", numeroKilos);

                //Para recoger los detalles
                Bundle miBundle3 = new Bundle();
                getDetalleClick(v);
                miBundle3.putString("DETALLES", strMessage);

                //Para recoger la tarifa
                Bundle miBundle4 = new Bundle();
                getTarifa(v);
                miBundle4.putString("TARIFAS", tarifaMsg);

                Bundle miBundle5 = new Bundle();
                PrecioTotal(v);
                miBundle5.putString("PRECIO", PrecioTotal);


                miIntent.putExtras(miBundle);
                miIntent.putExtras(miBundle2);
                miIntent.putExtras(miBundle3);
                miIntent.putExtras(miBundle4);

                startActivity(miIntent);
            }
        });
        detalles();
    }
    public void detalles() {
        regalo = (CheckBox) findViewById(R.id.regalo);
        tarjeta = (CheckBox) findViewById(R.id.tarjeta);

        regalo.setChecked(false);
        tarjeta.setChecked(false);
    }

    public void getDetalleClick(View v) {
        if (regalo.isChecked()) {
            strMessage = "Caja Regalo";
        }
        if (tarjeta.isChecked()) {
            strMessage = "Tarjeta Personalizada";
        }

        if (tarjeta.isChecked() && regalo.isChecked()) {
            strMessage = "Caja Regalo y Tarjeta Personalizada";
        }
    }

    public void PrecioTotal (View v) {
        PrecioTotal = String.valueOf(precio);
    }
    public void getTarifa(View v) {
        final RadioGroup selecgroup = (RadioGroup) findViewById(R.id.group);
        selecgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (selecgroup.getCheckedRadioButtonId() == R.id.TNormal) {
                    tarifaMsg = "Tarifa Normal";
                }
                if (selecgroup.getCheckedRadioButtonId() == R.id.TUrgente) {
                    precio = precio *0.30;
                    tarifaMsg = "Tarifa Urgente";
                }
            }
        });
    }
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}