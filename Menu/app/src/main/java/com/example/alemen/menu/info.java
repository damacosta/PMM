package com.example.alemen.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private void toast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    }

