package com.example.alemen.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                return true;
            case R.id.MnuOpc2:
                return true;
            case R.id.SubMnuOpc1:
                Intent rectangulo = new Intent(MainActivity.this, cuadrado.class);
                startActivity(rectangulo);
                return true;
            case R.id.SubMnuOpc2:
                Intent circulo = new Intent(MainActivity.this, circulo.class);
                startActivity(circulo);
                return true;
            case R.id.SubMnuOpc3:
                Intent random = new Intent(MainActivity.this, random.class);
                startActivity(random);
                return true;
            case R.id.MnuOpc3:
                Intent informacion = new Intent(MainActivity.this, info.class);
                startActivity(informacion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

