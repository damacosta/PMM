package com.example.alejandro.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity {
    TextView mName = null;
    TextView mPrice = null;
    //identificador de entrada
    Integer mRowId = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtención de extras, identificador y acción
        Bundle extras = getIntent().getExtras();
        mRowId = (savedInstanceState == null) ? null :
                (Integer) savedInstanceState.getSerializable(DataBaseHelper.SL_ID);
        if (mRowId == null) {
            mRowId = extras != null ? extras.getInt(DataBaseHelper.SL_ID): null;
        }
        // es solo para visualizar?
        if (extras != null && extras.getInt("action")== MainActivity.SHOW_ITEM) {
            setContentView(R.layout.detail_item);
        }
        else{
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
        }
        // obtener referencias
        mName = (TextView) findViewById(R.id.name);
        mPrice  = (TextView) findViewById(R.id.price);
        //identificador visible o no
        TableRow tr = (TableRow) findViewById(R.id.idRow);
        if (mRowId!=null){
            tr.setVisibility(View.VISIBLE);
          //  populateFieldsFromDB();
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

/*    protected void populateFieldsFromDB() {
        try {
            MainActivity.mDbHelper.open();
            Cursor c = MainActivity.mDbHelper.getItem(mRowId.intValue());
            if (c.moveToFirst()) {
                //diferentes maneras de obtener los datos del cursor
                //Mediante nombre de columna y lanza excepción si no existe
                mName.setText(c.getString(c.getColumnIndexOrThrow(DataBaseHelper.SL_NOMBRE)));
                //Mediante nombre de columna y devuelve -1 si no existe
                //Mediante posición del campo en el cursor
                mPrice.setText(Integer.toString(c.getColumnIndex(DataBaseHelper.SL_PRECIO)));
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
*/

    protected void saveData() {
        //obtener datos
        String itemText = mName.getText().toString();
        String importanceText = mPrice.getText().toString();
        try {
            MainActivity.mDbHelper.open();
            if (mRowId == null){
                //insertar
                MainActivity.mDbHelper.insertItem(itemText, 		Integer.parseInt(importanceText));
            }
            else{
                //actualizar
                //TextView tv = (TextView)findViewById(R.id.identificator);
                //String ident = tv.getText().toString();
                //MainActivity.mDbHelper.updateItem(Integer.parseInt(ident),itemText, Integer.parseInt(importanceText));
            }
            MainActivity.mDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }
}
