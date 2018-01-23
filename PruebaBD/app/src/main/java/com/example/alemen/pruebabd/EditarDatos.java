package com.example.alemen.pruebabd;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditarDatos extends AppCompatActivity {
    private Cliente[] datos;
    String oldNombre = "";
    String oldTelefono = "";
    String newNombre = "";
    String newTelefono = "";
    String mensaje = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);


        ClienteSQLite cliBDh = new ClienteSQLite(this, "DBClientes", null, 1);
        final SQLiteDatabase bd = cliBDh.getWritableDatabase();

        Rellenar();

        AdaptadorClientes adaptador = new AdaptadorClientes(this);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adaptador);

        final EditText Nombre = findViewById(R.id.nuevo_Nombre);
        final EditText Telefono = findViewById(R.id.nuevo_Telefono);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mensaje = "Nombre: " + datos[i].getNombre() + " Telefono: " + datos[i].getTelf();

                oldNombre = "" + datos[i].getNombre().toString();
                oldTelefono = "" + datos[i].getTelf().toString();

                Nombre.setText(oldNombre);
                Telefono.setText(oldTelefono);

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNombre = "" + Nombre.getText().toString();
                newTelefono = "" + Telefono.getText().toString();

                try {
                    //bd.execSQL("UPDATE Clientes set nombre = '" + newNombre + "' where nombre = '" + oldNombre + "'");
                    bd.execSQL("UPDATE Clientes set nombre = '" + newNombre + "', telefono = '" + newTelefono + "' where nombre = '" + oldNombre + "'");
                    Toast.makeText(getApplicationContext(), "ACTUALIZADO", Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fallo al guardar el nuevo cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            TextView nom = item.findViewById(R.id.nombre);
            nom.setText(datos[i].getNombre());

            TextView subtitulo = item.findViewById(R.id.telefono);
            subtitulo.setText(datos[i].getTelf());

            return item;
        }
    }

    public void Rellenar() {
        ClienteSQLite cliBDh = new ClienteSQLite(this, "DBClientes", null, 1);
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        String[] campos = new String[]{"nombre", "telefono"};
        Cursor c = bd.query("Clientes", campos, null, null, null, null, null);
        datos = new Cliente[c.getCount()];
        int i = 0;
        if (c.moveToFirst()) {
            do {
                String nombreCli = c.getString(0);
                String telefonoCli = c.getString(1);

                datos[i] = new Cliente(nombreCli, telefonoCli);
                i++;

            } while (c.moveToNext());
        }
    }
}
