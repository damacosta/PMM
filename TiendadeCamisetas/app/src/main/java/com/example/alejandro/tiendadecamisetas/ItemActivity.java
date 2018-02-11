package com.example.alejandro.tiendadecamisetas;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alejandro on 11/02/2018.
 */

public class ItemActivity extends Activity {

    TextView mNombre = null;
    TextView mPrecio = null;
    //identificador de entrada
    Integer mRowId = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtención de extras, identificador y acción
        Bundle extras = getIntent().getExtras();
        mRowId = (savedInstanceState == null) ? null :
                (Integer) savedInstanceState.getSerializable(DataBaseHelper.SL_ID);
        setContentView(R.layout.new_item);
        //botón de salvar
        Button saveBtn = (Button) findViewById(R.id.add);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                saveData();
                finish();
            }
        });

        // obtener referencias
        mNombre = (TextView) findViewById(R.id.name);
        mPrecio = (TextView) findViewById(R.id.price);

        TableRow tr = (TableRow) findViewById(R.id.idRow);
        if (mRowId!=null){
            tr.setVisibility(View.VISIBLE);
            populateFieldsFromDB();
        }
        else{
            tr.setVisibility(View.GONE);
        }
    }

    private void showMessage(int message) {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void populateFieldsFromDB() {
        try {
            MainActivity.mDbHelper.open();
            Cursor c = MainActivity.mDbHelper.getItem(mRowId.intValue());
            if (c.moveToFirst()) {
                //diferentes maneras de obtener los datos del cursor
                //Mediante nombre de columna y lanza excepción si no existe
                mNombre.setText(c.getString(c.getColumnIndexOrThrow(DataBaseHelper.SL_NOMBRE)));
                //Mediante nombre de columna y devuelve -1 si no existe
                //Mediante posición del campo en el cursor
                mPrecio.setText(Integer.toString(c.getInt(3)));
                TextView id = (TextView) findViewById(R.id.identificator);
                id.setText(Integer.toString(c.getInt(4)));
            }
            c.close();
            MainActivity.mDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }


    protected void saveData() {
        //obtener datos
        String itemText = mNombre.getText().toString();
        String importanceText = mPrecio.getText().toString();
        try {
            MainActivity.mDbHelper.open();
            if (mRowId == null){
                //insertar
                MainActivity.mDbHelper.insertItem(itemText,	Integer.parseInt(importanceText));
            }
            else{
                //actualizar
                TextView tv = (TextView)findViewById(R.id.identificator);
                String ident = tv.getText().toString();
                MainActivity.mDbHelper.updateItem(Integer.parseInt(ident),itemText, Integer.parseInt(importanceText));
            }
            MainActivity.mDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }
}
