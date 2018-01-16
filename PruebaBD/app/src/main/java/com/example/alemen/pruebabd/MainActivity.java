package com.example.alemen.pruebabd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Cliente[] datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos en modo escritura
        ClienteSQLite cliBDh = new ClienteSQLite(this, "DBClientes", null, 1);

        //Obtenemos referencia a la base de datos para poder modificarla.
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        //En caso de abrir de forma correcta la base de datos
            //Introducimos 3 clientes de ejemplo
            //      for (int cont=1; cont<=3; cont++) {
            //Creamos los datos
            //           int codigo = cont;
            //          String nombre = "Cliente" + cont;
            //          String telefono = cont + "XXXXXXX";

            //Introducimos los datos en la tabla Clientes
            //           bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
            //                   "VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
            //      }
            //Ejemplo Select1
            //   		String[] args3 = new String[]{"cli1"};
            //   		Cursor c = bd.rawQuery("SELECT nombre,telefono FROM Clientes WHERE nombre=? ", args3);

            //Ejemplo Select2
            String[] campos = new String[]{"nombre", "telefono"};
            Cursor c = bd.query("Clientes", campos, null, null, null, null, null);
            //Nos aseguramos de que exista al menos un registro
            datos = new Cliente[c.getCount()];
            int i = 0;
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya mas registros
                do {
                    String nombreCli = c.getString(0);
                    String telefonoCli = c.getString(1);

                    datos[i] = new Cliente(nombreCli, telefonoCli);
                    i++;

                } while (c.moveToNext());
            }

            bd.close();

            AdaptadorClientes adaptador = new AdaptadorClientes(this);
            Spinner spinner = findViewById(R.id.spinner);
            spinner.setAdapter(adaptador);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String mensaje = "Nombre: " + datos[i].getNombre() + " Telefono: " + datos[i].getTelf();
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            //Cerramos la base de datos
            // }
        }

        public class AdaptadorClientes extends ArrayAdapter {
            Activity context;

            AdaptadorClientes(Activity context) {
                super(context, R.layout.activity_cliente_sqlite, datos);
                this.context = context;
            }


            public View getDropDownView(final int position, View convertview, ViewGroup parent) {
                View vistadesplegada = getView(position, convertview, parent);

                return vistadesplegada;

            }

            public View getView(int i, View convertView, ViewGroup parent) {

                View item = convertView;
                if (item == null) {

                    LayoutInflater inflater = context.getLayoutInflater();
                    item = inflater.inflate(R.layout.activity_cliente_sqlite, null);
                }
                TextView nom = (TextView) item.findViewById(R.id.nombre);
                nom.setText(datos[i].getNombre());

                TextView subtitulo = (TextView) item.findViewById(R.id.telefono);
                subtitulo.setText(datos[i].getTelf());

                return item;
            }

        }



            //del if
    }
