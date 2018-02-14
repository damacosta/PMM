package com.example.alejandro.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //TODO AÑADIR FRAGMENTO CON MI INFORMACIÓN
                    return true;
                case R.id.navigation_dashboard:
                    //TODO ELIMINAR START ACTIVITY Y AÑADIR FRAGMENTO
                    Intent intent = new Intent(MainActivity.this, ViewPageHelper.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    //TODO AÑADIR BASE DE DATOS CON TODAS LAS PÁGINAS CREADAS
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
