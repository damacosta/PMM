package com.example.alemen.factoriales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FactorialAsynctask extends AppCompatActivity {

    EditText entrada;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial_asynctask);

        entrada = findViewById(R.id.activity_factorial_con_asynctask_et_entrada);
        salida = findViewById(R.id.activity_factorial_con_asynctask_tv_salida);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida.append(n + "! = ");
        MiTarea tarea = new MiTarea();
        tarea.execute(n);
    }

    public int factorial(int n) {
        int res=1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }

        return res;
    }

    class MiTarea extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... n) {
            return factorial(n[0]);
        }

        @Override
        protected void onPostExecute(Integer res) {
            salida.append(res + "\n");
        }
    }
}