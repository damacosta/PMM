package com.example.alemen.prueba;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStartA1", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResumeA1", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        Toast.makeText(this,"onPauseA1", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStopA1", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestartA1", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroyA1", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText miTexto = (EditText) findViewById(R.id.miTxt);
        miTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Borrado = "";
                miTexto.setText(Borrado);
            }
        });

        final Button miBoton = (Button) findViewById(R.id.miBtn);
        final TextView elSaludo = (TextView) findViewById(R.id.miLbl);

        Toast.makeText(this, "Estoy en la pantalla 1", Toast.LENGTH_SHORT).show();

        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent = new Intent(MainActivity.this, Pantalla2.class);

                Bundle miBundle = new Bundle();
                String mensajePaso = "Te saludo " + miTexto.getText();

                miBundle.putString("TEXTO", mensajePaso);
                miIntent.putExtras(miBundle);

                startActivity(miIntent);
            }
        });
    }
}