package com.example.alemen.pruebabd;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Guard;

public class IngresarDatos extends Activity {
    private Cliente[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_datos);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                GuardarRegistro();
                //String mensaje = "Cliente: " + Nombre + " con teléfono: " + Telefono + " guardado correctamente";
                //Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

            }
        });

    }

    protected void GuardarRegistro() {
        ClienteSQLite cliBDh = new ClienteSQLite(this, "DBClientes", null, 1);
        final SQLiteDatabase bd = cliBDh.getWritableDatabase();

        final EditText editNombre = findViewById(R.id.InsertarNombre);
        final EditText editTelefono = findViewById(R.id.InsertarTelefono);

        String Nombre = "" + editNombre.getText().toString();
        String Telefono = "" + editTelefono.getText().toString();
        int Codigo = 1;

        String mensaje = "Cliente: " + Nombre + " con teléfono: " + Telefono + " guardado correctamente";

        try {
            bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
                                       "VALUES (" + Codigo + ", '" + Nombre + "', '" + Telefono + "')");
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Fallo al guardar el nuevo cliente", Toast.LENGTH_SHORT).show();
        }


        bd.close();
    }
}
