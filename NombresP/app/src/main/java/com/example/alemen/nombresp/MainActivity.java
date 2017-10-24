package com.example.alemen.nombresp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer miSonido;

        private Titular[] datos = new Titular[]{

                new Titular("Perro", "Canis lupus familiaris", R.drawable.perro, R.raw.sonidoperro),
                new Titular("Gato", "Felis catus", R.drawable.gato, R.raw.sonidogato),
                new Titular("Ballena", "Balaenidae", R.drawable.ballena, R.raw.sonidoballena),
                new Titular("Canario", "Serinus Canaria", R.drawable.canario, R.raw.sonidocanario),
                new Titular("Pato", "Anas platyrhynchos", R.drawable.pato, R.raw.sonidopato),

        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        ListView lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Nombre: " + datos[position].getTitulo() + " Nombre científico: " + datos[position].getSubtitulo();
                miSonido = MediaPlayer.create(getApplicationContext(), datos[position].getMediaPlayer());
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                miSonido.start();}

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    class AdaptadorTitulares extends ArrayAdapter {
        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.listitem_titular, datos);
            this.context = context;
        }

        public View getView(int i, View converView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_titular, null);

            TextView lblTitulo = (TextView) item.findViewById(R.id.tvTitulo);
            lblTitulo.setText(datos[i].getTitulo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.tvSubtitulo);
            lblSubtitulo.setText(datos[i].getSubtitulo());

            ImageView imagen = (ImageView) item.findViewById(R.id.ivImagen);
            imagen.setBackground(getDrawable(datos[i].getImagen()));


            return (item);
        }
    }
}

