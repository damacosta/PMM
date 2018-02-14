package com.example.alejandro.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//TODO ACABAR BUSCADOR
public class ViewPage extends AppCompatActivity {

    Button visualizar;
    Button favoritos;
    EditText url;
    TextView textView;
    BottomNavigationView navigation;

    //Esto es el menú inferior generado automáticamente por Android Studio al elegir una actividad.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Intent intent = new Intent(ViewPage.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.navigation_dashboard:

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
        setContentView(R.layout.activity_view_page);

        textView = findViewById(R.id.textView);
        visualizar = findViewById(R.id.visualizar);
        favoritos = findViewById(R.id.favoritos);
        url = findViewById(R.id.url);

        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recoge el texto y lo añade al String newUrl para buscarlo
                String newUrl = "http://" + url.getText().toString();
                WebView webView = findViewById(R.id.webview);
                //Esto habilita JavaScript
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                //Aquí carga la dirección recogida anteriormente
                webView.loadUrl(newUrl);
                //Inicia la página web
                webView.setWebViewClient(new WebViewClient());
                Ocultar();
            }
        });
        navigation= findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void Ocultar() {
        //Oculta todos los elementos
        visualizar.setVisibility(View.INVISIBLE);
        favoritos.setVisibility(View.INVISIBLE);
        url.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        navigation.setVisibility(View.INVISIBLE);

        //Esto fuerza al teclado virtual a cerrarse (Al escribir en el EditText y no cerrar el teclado sigue apareciendo en la ventana del navegador)
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(visualizar.getWindowToken(), 0);

    }

}
