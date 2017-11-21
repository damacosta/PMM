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
        final RadioGroup selecgroup = (RadioGroup) findViewById(R.id.group);
        regalo = (CheckBox) findViewById(R.id.regalo);
        tarjeta = (CheckBox) findViewById(R.id.tarjeta);
        //Al pulsar el botón calcular hace todo esto
        botonpasar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent (MainActivity.this, resultado.class);
                //Para recoger la zona seleccionada
                Bundle miBundle = new Bundle();
                regalo.setChecked(false);
                tarjeta.setChecked(false);
                precio = 0;

                String mensajeSpinner = "" + zona.getSelectedItem();
                if (mensajeSpinner.equals("Zona A: Asia y Oceanía: 30 €")) {
                    precio = precio + 30;
                }

                if (mensajeSpinner.equals("Zona B: América y África 20 €")) {
                    precio = precio + 20;
                }

                if (mensajeSpinner.equals("Zona C: Europa 10 €")) {
                    precio = precio + 10;
                }

                miBundle.putString("ZONA", mensajeSpinner);

                //Para recoger los kilos del paquete
                String numeroKilos = "" + kilosnumero.getText();
                double numK = Double.parseDouble(numeroKilos);

                if (numK > 0 && numK < 6) {
                    precio = precio + numK;
                }
                if (numK > 6 && numK < 10) {
                    numK = numK * 1.5;
                    precio = precio + numK;
                }

                if (numK > 10) {
                    numK = numK * 2;
                    precio = precio + numK;
                }

                miBundle.putString("KILOS", "Kilos seleccionados: " + numeroKilos);

                //Para recoger los detalles
                if (regalo.isChecked()) {
                    strMessage = "Caja Regalo";
                }
                if (tarjeta.isChecked()) {
                    strMessage = "Tarjeta Personalizada";
                }

                if (tarjeta.isChecked() && regalo.isChecked()) {
                    strMessage = "Caja Regalo y Tarjeta Personalizada";
                }

                if (tarjeta.isChecked()==false && regalo.isChecked()==false) {
                    strMessage = "Sin detalles";
                }

                miBundle.putString("DETALLES", "Detalles elegidos: "+ strMessage);


                //Para recoger la tarifa
                if (selecgroup.getCheckedRadioButtonId() == R.id.TNormal) {
                    tarifaMsg = "Normal";
                }
                if (selecgroup.getCheckedRadioButtonId() == R.id.TUrgente) {
                    precio = precio *1.30;
                    tarifaMsg = "Urgente";
                }
                miBundle.putString("TARIFAS", "Tarifa seleccionada: " + tarifaMsg);


                PrecioTotal = String.valueOf(precio);
                miBundle.putString("PRECIO", "Precio total: " + PrecioTotal + "€");


                miIntent.putExtras(miBundle);

                startActivity(miIntent);
            }
        });
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}