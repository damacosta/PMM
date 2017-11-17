package com.example.alemen.examen1ev;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        final TextView Pizza= (TextView)findViewById(R.id.textView9);
        final TextView PrecioB= (TextView)findViewById(R.id.textView10);
        final TextView Extra= (TextView)findViewById(R.id.textView11);
        final TextView Unidades= (TextView)findViewById(R.id.textView12);
        final TextView Envio= (TextView)findViewById(R.id.textView13);
        final TextView Coste= (TextView)findViewById(R.id.textView14);
        final ImageView imagen = (ImageView)findViewById(R.id.imageView2);
        final AnalogClock c1 = (AnalogClock) findViewById(R.id.analogClock);
        final Button visible2 = (Button) findViewById(R.id.button3);

        c1.setVisibility(View.INVISIBLE);


        visible2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.setVisibility(View.VISIBLE);
            }
        });

        //Con checkbox no me funciona y por eso hago un boton
       // final CheckBox visible = (CheckBox) findViewById(R.id.checkBox5);
       /* if (visible.isChecked()) {
            c1.setVisibility(View.VISIBLE);
        } */




        Bundle  miBundleRecoger = getIntent().getExtras();
        Pizza.setText(miBundleRecoger.getString("PIZZA"));
        PrecioB.setText(miBundleRecoger.getString("PRECIO"));
        Extra.setText(miBundleRecoger.getString("EXTRA"));
        Unidades.setText(miBundleRecoger.getString("UNIDAD"));
        Envio.setText(miBundleRecoger.getString("SELECCION"));
        Coste.setText(miBundleRecoger.getString("TEXTO"));
        imagen.setBackgroundDrawable(getResources().getDrawable(miBundleRecoger.getInt("IMAGEN")));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent miIntent1 = new Intent(Resultado.this, Imagen.class);
                startActivity(miIntent1);
                return true;
            case R.id.MnuOpc2:
                Intent miIntent2 = new Intent(Resultado.this, Info.class);
                startActivity(miIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
