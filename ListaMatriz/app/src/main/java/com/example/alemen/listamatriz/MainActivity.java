package com.example.alemen.listamatriz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lista1 = (ListView)findViewById(R.id.lista1);
        lista1.setOnItemClickListener(new ListaInfo());
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class ListaInfo implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> lista, View selectedView,
                                int selectedIndex, long id) {
            String selection = lista.getItemAtPosition(selectedIndex).toString();
            showToast(selection);
        }
    }

    public void onNothingSelected(AdapterView<?> lista) {
        // Won’t be invoked unless you programmatically remove entries
    }
}