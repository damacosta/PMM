package com.example.alemen.complayout;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Calculadora extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        final EditText numero1 = (EditText) findViewById(R.id.numero1);
        final EditText numero2 = (EditText) findViewById(R.id.numero2);
        final EditText resultadooperacion = (EditText) findViewById(R.id.resultadooperacion);
        final RadioGroup seleccionOperacionGroup = (RadioGroup) findViewById(
                R.id.operacion);
        seleccionOperacionGroup.clearCheck();
        seleccionOperacionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                double resultado = 0;
                double num1 = Double.parseDouble(numero1.getText().toString());
                double num2 = Double.parseDouble(numero2.getText().toString());
                if (group.getCheckedRadioButtonId() == R.id.suma) {
                    resultado = num1 + num2;
                }
                if (group.getCheckedRadioButtonId() == R.id.resta) {
                    resultado = num1 - num2;
                }
                resultadooperacion.setText(String.valueOf(resultado));
            }
        });
    }
}