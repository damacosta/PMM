package com.example.alemen.prueba;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStartA2", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResumeA2", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPauseA2", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStopA2", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestartA2", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroyA2", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView otroSaludo = (TextView) findViewById(R.id.saludo2);

        Bundle recogerBundle = getIntent().getExtras();
        otroSaludo.setText(recogerBundle.getString("TEXTO"));

        showToast(recogerBundle.getString("TEXTO"));

        final MediaPlayer miMusica = MediaPlayer.create(getApplicationContext(), R.raw.smashmouth);
        miMusica.start();
    }


    protected void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        final ImageButton miBoton2 = (ImageButton) findViewById(R.id.botonvolver);

        miBoton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent miIntent2 = new Intent(Pantalla2.this, MainActivity.class);

                startActivity(miIntent2);
            }
        });
    }
}