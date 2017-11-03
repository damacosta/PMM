package com.example.alemen.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class rellenarC extends AppCompatActivity {

    TextView textView;

    Paint pincel1 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_c);

        textView = (TextView) findViewById(R.id.textView2);
        registerForContextMenu(textView);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout1);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {

            int ancho = canvas.getWidth();
            int largo = canvas.getHeight();
            pincel1.setStyle(Paint.Style.FILL);
            pincel1.setStrokeWidth(4);
            canvas.drawRect(40, 50, ancho -40, 500, pincel1);
        }
    }

    public boolean onContextItemSelected(MenuItem itemMnuContex) {

        switch (itemMnuContex.getItemId()) {
            case R.id.CtxLblOpc1:
                toast("YA ESTÁ RELLENADO, PRUEBA A VACIAR");
                return true;
            case R.id.CtxLblOpc2:
                toast("VACIADO");
                Intent cuadrado = new Intent(rellenarC.this, cuadrado.class);
                startActivity(cuadrado);
                return true;
            default:
                return super.onContextItemSelected(itemMnuContex);
        }
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
                Intent rectangulo = new Intent(rellenarC.this, cuadrado.class);
                startActivity(rectangulo);
                return true;
            case R.id.SubMnuOpc2:
                Intent circulo = new Intent(rellenarC.this, circulo.class);
                startActivity(circulo);
                return true;
            case R.id.MnuOpc3:
                Intent informacion = new Intent(rellenarC.this, info.class);
                startActivity(informacion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

}
