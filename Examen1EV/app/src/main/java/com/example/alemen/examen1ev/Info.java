package com.example.alemen.examen1ev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);}


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinfo, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent miIntent1 = new Intent(Info.this, MainActivity.class);
                startActivity(miIntent1);
                return true;
            case R.id.MnuOpc2:
                Intent miIntent2 = new Intent(Info.this, Imagen.class);
                startActivity(miIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
