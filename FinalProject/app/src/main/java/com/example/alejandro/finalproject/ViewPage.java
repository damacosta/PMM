package com.example.alejandro.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import java.sql.SQLException;

import static com.example.alejandro.finalproject.FavoritePages.mDbHelper;

//TODO HACER ALERTDIALOG PARA GUARDAR NOMBRE DE PÁGINA WEB
public class ViewPage extends Activity {

    Button visualizar;
    Button favoritos;
    EditText url;
    TextView textView;
    BottomNavigationView navigation;
    EditText input;
    String newNombre;
    String newUrl;
    AlertDialog.Builder alert;

    Integer mRowId = null;

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
                Intent intent2 = new Intent(ViewPage.this, FavoritePages.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        mDbHelper = new DataBaseHelper(this);

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

        //Crea un nuevo AlertDialog
        alert = new AlertDialog.Builder(this);

        //Le añade el títu lo y el mensaje
        alert.setTitle(R.string.titledialog);
        alert.setMessage(R.string.messagedialog);
        //Crea un EditText y se lo asigna al AlertDialog creado anteriormente
        input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Recoge los datos del AlertDialog y del EditText de la url a buuscar y los inserta en la BD cuando pulses Ok
                mDbHelper.open();
                if (mRowId == null){
                    newNombre = input.getText().toString();
                    newUrl = "http://" + url.getText().toString();
                    mDbHelper.insertItem(newNombre, newUrl);
                    showMessage(R.string.guardado);
                }

            }
        });

        alert.setNegativeButton(R.string.cancelar,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Cuando pulses cancelar, cancelará el AlertDialog
                        dialog.cancel();
                    }
                });

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Muestra el AlertDialog
                alert.show();
            }
        });

        navigation= findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void showMessage(int message) {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void Ocultar() {
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
