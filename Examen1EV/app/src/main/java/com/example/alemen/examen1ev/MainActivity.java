package com.example.alemen.examen1ev;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private String pizza[] = {"MARGARITA 12€", "TRES QUESOS 15€", "BARBACOA 18€", "ESPECIAL 21€"};
    private String ingredientes[] = {"jamon/tomate", "queso1/queso2", "carne/tomate", "ingredientes secretos"};
    private double[] precio = {12, 15, 18, 21};
    private int[] imagenes = {R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4};
    private int imagenSeleccionada;
    private double precioSeleccionado;

    private Spinner Spinner;
    private CheckBox grande, ingred, queso;
    private RadioButton local, domicilio;
    private EditText numero;
    private Button calcular;
    private TextView dinero;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent miIntent1 = new Intent(MainActivity.this, Imagen.class);
                startActivity(miIntent1);
                return true;
            case R.id.MnuOpc2:
                Intent miIntent2 = new Intent(MainActivity.this, Info.class);
                startActivity(miIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner = (Spinner) findViewById(R.id.spinner);
        grande = (CheckBox) findViewById(R.id.checkBox);
        ingred = (CheckBox) findViewById(R.id.checkBox2);
        queso = (CheckBox) findViewById(R.id.checkBox3);
        local = (RadioButton) findViewById(R.id.radioButton);
        domicilio = (RadioButton) findViewById(R.id.radioButton2);
        numero = (EditText) findViewById(R.id.editText);
        calcular = (Button) findViewById(R.id.button);
        dinero = (TextView) findViewById(R.id.textView2);

        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizza);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(miAdaptador);

        //Cuando pulses en el número de pizzas que quieres se borra automaticamente para que no de error
        numero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero.setText("");
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            double total = 0;
                                            double total1 = 0;
                                            String box = " ";
                                            String sele = " ";
                                            int extras = 0;
                                            String n = numero.getText().toString();
                                            double num = Double.parseDouble(n);
                                            String seleccion = Spinner.getSelectedItem().toString();
                                            if (seleccion.equals("MARGARITA 12€")) {
                                                total1 += 12;
                                                imagenSeleccionada = imagenes[0];
                                                precioSeleccionado = precio[0];
                                            }
                                            if (seleccion.equals("TRES QUESOS 15€")) {
                                                total1 += 15;
                                                imagenSeleccionada = imagenes[1];
                                                precioSeleccionado = precio[1];
                                            }
                                            if (seleccion.equals("BARBACOA 18€")) {
                                                total1 += 18;
                                                imagenSeleccionada = imagenes[2];
                                                precioSeleccionado = precio[2];
                                            }
                                            if (seleccion.equals("ESPECIAL 21€")) {
                                                total1 += 21;
                                                imagenSeleccionada = imagenes[3];
                                                precioSeleccionado = precio[3];
                                            }

                                            if (grande.isChecked()) {
                                                total1 += 1;
                                                extras +=1 ;
                                            }
                                            if (ingred.isChecked()) {
                                                total1 += 1;
                                                extras +=1 ;
                                            }
                                            if (queso.isChecked()) {
                                                total1 += 1;
                                                extras +=1 ;
                                            }
                                            if (num >= 0) {
                                                total1 = total1 * num;
                                            }
                                            if (domicilio.isChecked()) {
                                                total = total1 * 0.1;
                                                total1 += total;
                                                box = "DOMICILIO";
                                            }
                                            if (local.isChecked()) {
                                                box = "LOCAL";
                                            }
                                            String mensaje = "" + total1 + "€";
                                            dinero.setText(mensaje);

                                            Intent miIntent = new Intent(MainActivity.this, Resultado.class);
                                            Bundle miBundle = new Bundle();
                                            String pizzaSeleccion = "" + seleccion;
                                            String unidad = "" + num;
                                            String mensajeEnvio = "" + box;
                                            String extra = "" + extras;
                                            String precioBase = "" + precioSeleccionado;
                                            String precioFinal = "" + total1;
                                            miBundle.putString("PIZZA", pizzaSeleccion);
                                            miBundle.putString("UNIDAD", unidad);
                                            miBundle.putString("SELECCION", mensajeEnvio);
                                            miBundle.putString("TEXTO", precioFinal);
                                            miBundle.putString("PRECIO", precioBase);
                                            miBundle.putString("EXTRA", extra);
                                            miBundle.putInt("IMAGEN", imagenSeleccionada);
                                            miIntent.putExtras(miBundle);
                                            startActivity(miIntent);
                                        }
                                    }
        );

    }
}