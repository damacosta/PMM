package com.example.alemen.layout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class Pantalla1 extends AppCompatActivity {

    RadioButton radioR;
    RadioButton radioG;
    RadioButton radioB;
    Button botonSet;
    Button botonDelete;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);
        initialUISetup();
    }

    public void initialUISetup() {
        radioR = (RadioButton) findViewById(R.id.radio1);
        radioG = (RadioButton) findViewById(R.id.radio2);
        radioB = (RadioButton) findViewById(R.id.radio3);
        botonSet = (Button) findViewById(R.id.button1);
        botonDelete = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);

        botonSet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getColor(v);
            }
        });

        botonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setBackgroundColor(Color.WHITE);
            }
        });
    }

    public void getColor(View v) {
        if (radioR.isChecked()) {
            textView.setBackgroundColor(Color.RED);
        }
        if (radioG.isChecked()) {
            textView.setBackgroundColor(Color.GREEN);
        }
        if (radioB.isChecked()) {
            textView.setBackgroundColor(Color.BLUE);
        }
    }
}