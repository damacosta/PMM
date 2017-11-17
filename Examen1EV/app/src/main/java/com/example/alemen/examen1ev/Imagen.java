package com.example.alemen.examen1ev;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Imagen extends AppCompatActivity {

    TextView textView;

    Paint pincel1 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        textView = (TextView) findViewById(R.id.textView2);
        registerForContextMenu(textView);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout1);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuim, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent miIntent1 = new Intent(Imagen.this, MainActivity.class);
                startActivity(miIntent1);
                return true;
            case R.id.MnuOpc2:
                Intent miIntent2 = new Intent(Imagen.this, Info.class);
                startActivity(miIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            pincel1.setStyle(Paint.Style.STROKE);
            pincel1.setStrokeWidth(4);

            canvas.drawCircle(550,500,300,pincel1);

            canvas.drawLine(550,200,550,800,pincel1);
            canvas.drawLine(250,500,850,500,pincel1);

            canvas.drawCircle(350,400,35,pincel1);
            canvas.drawCircle(750,400,35,pincel1);

            canvas.drawCircle(450,300,35,pincel1);
            canvas.drawCircle(650,300,35,pincel1);

            canvas.drawCircle(750,600,35,pincel1);
            canvas.drawCircle(650,700,35,pincel1);

            canvas.drawCircle(450,700,35,pincel1);
            canvas.drawCircle(350,600,35,pincel1);









        }
    }
}
