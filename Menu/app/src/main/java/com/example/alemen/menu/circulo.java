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

public class circulo extends AppCompatActivity {

    TextView textView;

    Paint pincel1 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulo);
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
            int x = canvas.getWidth();
            int y = canvas.getWidth();
            pincel1.setStyle(Paint.Style.STROKE);
            pincel1.setStrokeWidth(4);

            canvas.drawCircle(x/2,y/2,300,pincel1);

        }
    }

    public boolean onContextItemSelected(MenuItem itemMnuContex) {

        switch (itemMnuContex.getItemId()) {
            case R.id.CtxLblOpc1:
                toast("RELLENADO");
                Intent cuadrado = new Intent(this, rellenarcirculo.class);
                startActivity(cuadrado);
                return true;
            case R.id.CtxLblOpc2:
                toast("YA ESTÁ VACIADO, PRUEBA A RELLENAR");
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
                Intent rectangulo = new Intent(circulo.this, cuadrado.class);
                startActivity(rectangulo);
                return true;
            case R.id.SubMnuOpc2:
                Intent circulo = new Intent(circulo.this, circulo.class);
                startActivity(circulo);
                return true;
            case R.id.MnuOpc3:
                Intent informacion = new Intent(circulo.this, info.class);
                startActivity(informacion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
