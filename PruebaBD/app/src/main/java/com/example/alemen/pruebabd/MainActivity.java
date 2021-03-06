package com.example.alemen.pruebabd;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    public static final int NEW_ITEM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Introducimos 3 clientes de ejemplo
        //      for (int cont=1; cont<=3; cont++) {
        //           int codigo = cont;
        //          String nombre = "Cliente" + cont;
        //          String telefono = cont + "XXXXXXX";
        //           bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
        //                   "VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
        //      }

        //Ejemplo Select1
        //   		String[] args3 = new String[]{"cli1"};
        //   		Cursor c = bd.rawQuery("SELECT nombre,telefono FROM Clientes WHERE nombre=? ", args3);


        Rellenar();

        AdaptadorClientes adaptador = new AdaptadorClientes(this);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Rellenar();
                String mensaje = "Nombre: " + datos[i].getNombre() + " Telefono: " + datos[i].getTelf();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent intent = new Intent(MainActivity.this, IngresarDatos.class);
                startActivityForResult(intent, NEW_ITEM);
                return true;
            case R.id.MnuOpc2:
                Intent intent2 = new Intent(MainActivity.this, EditarDatos.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Rellenar();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al rellenar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
