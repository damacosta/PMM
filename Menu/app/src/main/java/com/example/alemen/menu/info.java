package com.example.alemen.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;


public class info extends AppCompatActivity {

    SmallBang smallBang;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        smallBang = SmallBang.attach2Window(this);

        textView = (TextView) findViewById(R.id.nombre);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redText(v);
            }
        });
    }

        public void redText(View view){
            textView.setTextColor(0xFFCD8BF8);
            smallBang.bang(view,50,new SmallBangListener() {
                @Override
                public void onAnimationStart() {
                }

                @Override
                public void onAnimationEnd() {
                    toast("Cursando Desarrollo de Aplicaciones Multiplataforma");
                }
            });
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
                Intent rectangulo = new Intent(info.this, cuadrado.class);
                startActivity(rectangulo);
                return true;
            case R.id.SubMnuOpc2:
                Intent circulo = new Intent(info.this, circulo.class);
                startActivity(circulo);
                return true;
            case R.id.MnuOpc3:
                Intent informacion = new Intent(info.this, info.class);
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

