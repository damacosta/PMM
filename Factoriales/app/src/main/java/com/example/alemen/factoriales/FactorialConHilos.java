package com.example.alemen.factoriales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FactorialConHilos extends AppCompatActivity {
    EditText entrada;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial_con_hilos);

        entrada = findViewById(R.id.activity_factorial_con_hilos_et_entrada);
        salida = findViewById(R.id.activity_factorial_con_hilos_tv_salida);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida.append(n + "! = ");
        MiThread thread = new MiThread(n);
        thread.start();
    }

    public int factorial(int n) {
        int res=1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }

        return res;
    }

    class MiThread extends Thread {
        private int n, res;
        public MiThread(int n) {
            this.n = n;
        }

        @Override
        public void run () {
            res = factorial(n);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida.append(res + "\n");
                }
            });
        }
    }
}